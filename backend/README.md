[![pipeline status](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/badges/master/pipeline.svg)](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/commits/master)

# Subadvisor - Spring Backend - v0.0.1

Look at the current [Swagger!](./backend-swagger.yml "BACKEND SWAGGERE")

## Prerequisites

If you want to run the application local you have to install:

+ Maven
+ Java 11

An Alternative is to use our [~~Docker~~ (deprecated)](#@deprecated-~~usage-with-docker~~) or better [docker-compose](../README.md#starting-with-docker-compose "start with docker-compose")

## Usage locally
```bash
# run the application (includes packaging)
make run-be

# stop the application (includes a cleanup)
make stop-be
```

## Testing

```bash
# run Unit-Tests for backend
make test-unit

# run Integration-tests
make test-it

# run all tests together
make run test-all
```

## Changelog

## Usage with Docker-Compose

Since Dev-mode is also supported for Spring-backend (changes are mirrored automatilcy via volumes in the running container) we support development with docker-compose.

Read the [README](../README.md "BACKEND REAMDE") of the whole project for further instructions.

## Deployment with Heroku

SPRING_PROFILES_ACTIVE=prod is set via heroku config:set and not passed as env-variable in docker run.

## @Deprecated ~~Usage with Docker~~ 

You can run the backendservice as a docker-container on port 8080.
The advantage to choose Subadvisor with Docker is that you do not need a 
local installation of Maven or Java JDK.
You just need a Docker Daemon to run the Container.

```bash
# build the image
make d-build

# run a container from a previously build image
make d-run

# stop and start a container
make d-stop
make d-start

# restart a container
make d-restart

# stop and remove all running container and images
make d-clean

# start the day with a good and clean complete
make d-complete
```

### Set environment-variables

There are plenty ways to set environment-variables.

```bash
java -jar target/*.jar --server.port=8768

# when using -D it comes right after the java-command
java -Dserver.port=9999 -jar target/*.jar 
```