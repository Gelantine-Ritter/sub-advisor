SUBADVISOR_IMAGE=subadvisor/backend
SUBADVISOR_CONTAINER=subadvisor-backend

run-be:
	cd backend && ./mvnw clean && ./mvnw package && java -jar target/*.jar

stop-be:
	cd backend && ./mvnw clean spring-boot:stop

mvn-package-and-clean:
	cd backend && ./mvnw clean && ./mvnw package

d-build:
	cd backend && docker build -t $(SUBADVISOR_IMAGE) .

d-run:
	. scripts/docker-backend-helper.sh && docker_helper run

d-start:
	. scripts/docker-backend-helper.sh && docker_helper start

d-stop:
	. scripts/docker-backend-helper.sh && docker_helper stop

d-clean:
	. scripts/docker-backend-helper.sh && docker_helper clean

d-complete: d-clean d-build d-run

d-restart: d-stop d-start



