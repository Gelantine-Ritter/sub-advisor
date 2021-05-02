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

If you run in a problem, you can stop and remove all running container and images with:
```bash
make d-clean
```
You can also restart your container with:
```bash
make d-restart
```
A good way to start the working day is to clean everything, build a new Image and start a fresh container with:
```bash
make d-complete
```