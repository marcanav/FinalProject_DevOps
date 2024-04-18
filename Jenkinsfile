pipeline {
    agent any

    tools {
        maven "MAVEN3"
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
            agent any
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

        
    }
}
