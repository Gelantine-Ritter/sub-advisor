run_backend:
	cd backend && ./mvnw clean spring-boot:run

stop_backend:
	cd backend && ./mvnw clean spring-boot:stop

test:
	echo "testing whole app"
