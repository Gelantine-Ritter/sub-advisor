SUBADVISOR_IMAGE=subadvisor/backend
SUBADVISOR_CONTAINER=subadvisor-backend
HEROKU_API_KEY=c4378700-f98e-4027-a5e9-27fe4b1f0e3e
APP_NAME_BACKEND=subadvisor-backend
APP_NAME_FRONTEND=subadvisor-frontend

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
	cd backend && mvn clean test-compile -Dspring.profiles.active=default failsafe:integration-test failsafe:verify



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

# ----- Docker for heroku production ---#
d-login-heroku:
	docker login -u _ -p $(HEROKU_API_KEY) registry.heroku.com
d-build-backend-prod:
	cd backend && docker build --file=Dockerfile --rm=true -t registry.heroku.com/$(APP_NAME_BACKEND)/web .
d-push-backend-prod:
	docker push registry.heroku.com/$(APP_NAME_BACKEND)/web
d-run-backend-prod:
	docker run -e HEROKU_API_KEY=$(HEROKU_API_KEY) wingrunr21/alpine-heroku-cli:latest container:release web -a $(APP_NAME_BACKEND)
d-build-frontend-prod:
	cd frontend && docker build --file=Dockerfile --rm=true -t registry.heroku.com/$(APP_NAME_FRONTEND)/web .
d-push-frontend-prod:
	docker push registry.heroku.com/$(APP_NAME_FRONTEND)/web
d-run-frontend-prod:
	docker run -e HEROKU_API_KEY=$(HEROKU_API_KEY) wingrunr21/alpine-heroku-cli:latest container:release web -a $(APP_NAME_FRONTEND)

deploy-backend-prod: d-login-heroku d-build-backend-prod d-push-backend-prod d-run-backend-prod

# ----- DEPRECATED ------ #
# ----- using backend docker ----- #

d-build:
	cd backend && docker build -t $(SUBADVISOR_IMAGE) .
d-run:
	docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=local -e TEST=test-local --name $(SUBADVISOR_CONTAINER) $(SUBADVISOR_IMAGE)
d-stop:
	docker stop $(SUBADVISOR_CONTAINER)
d-start:
	. scripts/docker-backend-helper.sh && docker_helper start
d-clean:
	. scripts/docker-backend-helper.sh && docker_helper clean

d-complete: d-clean d-build d-run
d-restart: d-stop d-start