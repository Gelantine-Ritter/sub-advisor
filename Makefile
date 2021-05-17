SUBADVISOR_IMAGE=subadvisor/backend
SUBADVISOR_CONTAINER=subadvisor-backend

p?=dev

mvn-clean-package:
	cd backend && ./mvnw clean package -Dmaven.test.skip && ./mvnw test-compile

# this show how to inject programm-args directly from cli to spring
# access the argument via @Value({DB_HOST}) in the code or in application.properties with ${DB_HOST}
test-prog-args: mvn-clean-package
	cd backend && java -jar target/*.jar --DB_HOST="postgres"

run-be-with-profil:
	cd backend && java -jar -Dspring.profiles.active=$(p) target/*.jar

# 		you can pass an env-variable to specify the backend-profile:
# 		make p=dev run-be
# 		if not specified, it will run with p=default
run-be: mvn-clean-package run-be-with-profil

stop-be:
	cd backend && ./mvnw clean spring-boot:stop

test-unit:
	cd backend && ./mvnw clean && ./mvnw test

test-all:
	cd backend && ./mvnw clean && ./mvnw verify

test-it:
	 cd backend && mvn clean test-compile -Dspring.profiles.active=default failsafe:integration-test

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


# docker-compose

dc-complete: dc-clean
	docker-compose up --build

dc-up:
	docker-compose up

dc-stop:
	docker-compose stop

dc-rm-volume:
	docker-compose down -v

dc-clean: dc-stop
	docker-compose down --rmi all

dc-doom: dc-rm-volume dc-clean

dc-test-it:




