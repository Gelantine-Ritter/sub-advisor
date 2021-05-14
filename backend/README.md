[![pipeline status](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/badges/master/pipeline.svg)](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/commits/master)

# Subadvisor - Spring Backend - v0.0.1

Look at the current [Swagger!](./backend-swagger.yml "BACKEND SWAGGERE")

## Prerequisites

If you want to run the application local you have to install:

+ Maven
+ Java 11

An Alternative is to use our [Docker-System](#usage-with-docker)

## Usage locally
```bash
# run the application (includes packaging)
make run-be

# stop the application (includes a cleanup)
make stop-be
```

## Test the API

## Changelog

## Usage with Docker

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