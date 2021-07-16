[![The MIT License](https://img.shields.io/badge/license-MIT-yellowgreen.svg?style=flat-square)](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/blob/master/LISCENSE.md)
[![pipeline status](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/badges/master/pipeline.svg)](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/commits/master)

# Subadvisor

[Subadvisor](https://subadvisor.herokuapp.com) is a platform for cultural venues that want to promote their events as well as for all those curious to know what's going on outside or online since the pandemic. We believe that culture makers are as much important as cultural events. subadvisor is not only an event calendar. We wanted to make it a place to discover the cultural places and artists too. Our project started in 2021 during the pandemic. Since the first lockdown, many events had to be cancelled or moved online. Missing concerts, parties and cultural experiences has led us to use these constrained months to develop this platform and reconnect with the emotions that cultural places offer us.

## Table of Content

- [Subadvisor](#subadvisor)
  - [Table of Content](#table-of-content)
  - [Releases](#releases)
  - [Build With](#build-with)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
      - [Installing](#installing)
      - [Docker](#docker)
    - [Docker-Compose](#docker-compose)
    - [SubAvisor Backend](#subavisor-backend)
    - [SubAvisor Frontend](#subavisor-frontend)
    - [Codebase](#codebase)
    - [Authors](#authors)
    - [License](#license)

## Releases

+ [v1.0.0](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/releases/v1.0.0)
+ [v2.0.0](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/releases/v2.0.0)
+ [v3.0.0](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/releases/v3.0.0)

## Build With

- [Spring](https://spring.io "Spring's Homepage")
- [Vue](https://vuejs.org "Vue's Homepage")
- [PostgreSQL](https://www.postgresql.org, "PostgreSQL's Homepage")

## Getting Started

### Prerequisites

#### Installing

Use git clone to get a copy of the projects repository:

```bash
git clone git@gitlab.beuth-hochschule.de:mi-project/sub-advisor.git
```

#### Docker

Just go on [official Docker website](https://docs.docker.com/engine/install/) and download the installer or install it via terminal.

### Docker-Compose

We recommend to start our project as a docker-cluster with docker-compose.
This will start the spring-backend, vue-frontend and postgres-db as dev-docker-container.

```bash
# start the cluster
make dc-up

# stop the cluster
make dc-stop

# stop and remove all container and images
make dc-clean

# all-in-one (remove all images and container, create new ones and bring them up)
make dc-complete

# kill everything - even the volumes
make dc-doom
```

The docker-compose dev-environment will mirror all code-changes via volumes into the running container. BUT: Since Java
has to be compile first you have to activate classpath-rebuilding inside your IDE when developing backend-code.
Please check out this [article](https://mkyong.com/spring-boot/intellij-idea-spring-boot-template-reload-is-not-working/ "BACKEND REAMDE")
when you are using IntelliJ IDEA to enable Spring Boot Dev Tools.

### SubAvisor Backend

Read the [READme!](./backend/README.md "BACKEND REAMDE")

### SubAvisor Frontend

Read the [READme!](./frontend/README.md "FRONTEND REAMDE")

### Codebase

[subadvisor](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor)

### Authors

- Anna Reißmann
- Jessica Lorillu
- Kilian Klammt
- Matti Henning

### License

Distributed under the MIT License. See [`LICENSE.md`](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/blob/master/LISCENSE.md) for more information.
