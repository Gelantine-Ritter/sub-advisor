SUBADVISOR_IMAGE=subadvisor/backend
SUBADVISOR_CONTAINER=subadvisor-backend

p?=dev

# ----- docker-compose ----- #

dc-complete: dc-clean
	docker-compose up $(ARGS) --build

dc-up:
	docker-compose up

dc-stop:
	docker-compose stop

dc-rm-volume:
	docker-compose down -v

dc-clean: dc-stop
	docker-compose down --rmi all

dc-doom: dc-rm-volume dc-clean

# ----- test backend ----- #

# when testing the default-profile is used -> H2 instead of Postgres

test-unit:
	cd backend && ./mvnw clean && ./mvnw test

test-all:
	cd backend && ./mvnw clean && ./mvnw verify

test-it:
	cd backend && mvn clean test-compile -Dspring.profiles.active=default failsafe:integration-test



# ----- building backend ----- #

mvn-clean-package:
	cd backend && ./mvnw clean package -Dmaven.test.skip && ./mvnw test-compile

run-be-with-profil:
	cd backend && java -jar -Dspring.profiles.active=$(p) target/*.jar

# 		you can pass an env-variable to specify the backend-profile:
# 		make p=dev run-be
# 		if not specified, it will run with p=default
run-be: mvn-clean-package run-be-with-profil

stop-be:
	cd backend && ./mvnw clean spring-boot:stop

# ----- DEPRECATED ------ #
# ----- using backend docker ----- #

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







