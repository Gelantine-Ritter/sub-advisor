### Start the Backend with Docker
You can run the backendservice as a docker-container on port 8080.

Build the Image with:
```bash
make d-build
```

Then,run a container with:
```bash
make d-start
```
You can also stop and restart running container with:
```bash
make d-stop
make d-start
```

If you run in a problem, you can stop and remove al running container and images with:
```bash
make d-clean
```