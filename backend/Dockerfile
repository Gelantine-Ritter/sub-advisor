# First Step - packaging with maven
FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

# Second step - lose maven-context and hold only executable .jar-file
FROM openjdk:11-jre-slim-buster

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /target/*.jar /backend.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/backend.jar"]