[![pipeline status](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/badges/master/pipeline.svg)](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/commits/master)

# Subadvisor - Spring Backend - v0.0.0

## Prerequisites

If you want to run the application local you have to install:

+ Maven
+ Java 11

An Alternative is to use our [Docker-System](#usage-with-docker)

### Usage locally

### Test the API



## Changelog

## Usage with Docker

You can run the backendservice as a docker-container on port 8080.
The advantage to choose Subadvisor with Docker is that you do not need a 
local installation of Maven or Java JDK.
You just need a Docker Daemon to run the Container.

Build the Image with:
```bash
make d-build
```

Then, run a container with:
```bash
make d-run
```
You can also stop and restart running container with:
```bash
make d-stop
make d-start
```

If you run in a problem, you can stop and remove all running container and images with:
```bash
make d-clean
```
You can also restart your container with:
```bash
make d-restart
```
A good way to start the working day is to clean everything, build a new Image and start a fresh container with:
```bash
make d-complete
```