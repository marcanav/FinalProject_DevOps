# Use Maven base image to build the project
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use Tomcat for the runtime image
FROM tomcat:9.0-jdk11-openjdk-slim
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/myapp.war
EXPOSE 8080