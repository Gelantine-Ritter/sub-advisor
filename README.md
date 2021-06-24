[![pipeline status](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/badges/master/pipeline.svg)](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/commits/master)

# Subadvisor - v0.0.1

# Changelog

[v1.0.0](https://gitlab.beuth-hochschule.de/mi-project/sub-advisor/-/releases/v1.0.0)

### Starting with Docker-Compose

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

Read the [README!](./backend/README.md "BACKEND REAMDE")
