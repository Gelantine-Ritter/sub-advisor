run-be:
	cd backend && ./mvnw clean && ./mvnw package && java -jar target/*.jar

stop-be:
	cd backend && ./mvnw clean spring-boot:stop

test:
	echo "testing whole app"

mvn-package-and-clean:
	cd backend && ./mvnw clean && ./mvnw package

d-build-be: mvn-package-and-clean
	cd backend && docker build -t springio/gs-spring-boot-docker .

d-start-be:
	docker run -p 8080:8080 springio/gs-spring-boot-docker --name subadvisor-backend