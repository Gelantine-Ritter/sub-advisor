run_backend:
	cd backend && ./mvnw clean && ./mvnw package && java -jar target/backend-0.0.1-SNAPSHOT.jar

stop_backend:
	cd backend && ./mvnw clean spring-boot:stop

test:
	echo "testing whole app"
