pipeline {
    agent any

    tools {
        maven "MAVEN3"
        jdk 'JDK17'
    }

    environment {
        JAVA_HOME = 'C:/Program Files/Java/jdk-17'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Running build ...'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage("SonarQube analysis") {
            steps {
              withSonarQubeEnv('SonarQubeServer') {
                bat 'mvn clean package sonar:sonar'
              }
            }
        }

        stage("Quality Gate") {
            steps {
              timeout(time: 2, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
              }
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
                script {
                    def jacocoReportPath = '**/target/site/jacoco/*.html'
                    archiveArtifacts artifacts: jacocoReportPath, fingerprint: true
                }
            }
        }

        stage('Deploy to Development') {
            when {
                branch 'develop'
            }
            steps {
                script {
                    // Assuming you're deploying a web app to a remote Tomcat server via SSH
                    sshPublisher(
                        publishers: [
                            sshPublisherDesc(
                                configName: "dev-server",
                                transfers: [
                                    sshTransfer(
                                        sourceFiles: "target/*.jar",
                                        removePrefix: "target",
                                        remoteDirectory: "webapps/",
                                        execCommand: "sh restart_tomcat.sh" // Script to restart Tomcat or deploy the app
                                    )
                                ]
                            )
                        ]
                    )
                }
            }
        }
    }
}
