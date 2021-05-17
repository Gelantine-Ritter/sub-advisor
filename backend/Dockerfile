# First Step - packaging with maven
FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD
WORKDIR /app
COPY ./ /app

# important to skip tests since there ath this point there
# is no database-configuration
RUN mvn clean package -Dmaven.test.skip

# Second step - lose maven-context and hold only executable .jar-file
FROM openjdk:11-jre-slim-buster
VOLUME /tmp
WORKDIR /backend

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /app/target/*.jar /backend/app.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "app.jar"]