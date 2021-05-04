#!/bin/bash

SUBADVISOR_IMAGE=subadvisor/backend
SUBADVISOR_CONTAINER=subadvisor-backend

CONTAINER_RUNNING=$(docker ps -q -f name=$SUBADVISOR_CONTAINER)
CONTAINER_PRESENT=$(docker ps -aq -f name=$SUBADVISOR_CONTAINER)
AMOUNT_CONTAINERS_PRESENT=0;
AMOUNT_CONTAINERS_RUNNING=0;
IMAGE_PRESENT=$(docker images $SUBADVISOR_IMAGE)


STEP=1
SUB_COMMAND="[SUB_COMMAND] $STEP:"


increase() {
  ((STEP=$STEP+1))
  SUB_COMMAND="[SUB_COMMAND] $STEP:"
}

count_containers_present() {
  for c in $CONTAINER_PRESENT; do
    ((AMOUNT_CONTAINERS_PRESENT=$AMOUNT_CONTAINERS_PRESENT+1))
  done
}

container_running() {
  docker ps -q -f name=$SUBADVISOR_CONTAINER
}

count_containers_running() {
  for c in $CONTAINER_RUNNING; do
    ((AMOUNT_CONTAINERS_RUNNING=$AMOUNT_CONTAINERS_RUNNING+1))
  done
}

count_images(){
  f
}

docker_helper() {
    count_containers_present
    count_containers_running
    case "$1" in
      start) start; return ;;
      clean) clean; return ;;
      stop) stop; return ;;
      run) run; return ;;
    esac
}

# docker-command-functions:

start() {
  if [ $CONTAINER_RUNNING ]
  then
    echo "$SUB_COMMAND there are already sub-advisor-container running.
          If you have a problem with one of them run d-clean" && increase
  elif [ $AMOUNT_CONTAINERS_PRESENT -gt  2 ]
  then
    echo "$SUB_COMMAND there are more then one stopped sub-advisor-container. Please remove unrequired containers
          If you have a problem run d-clean or d-complete" && increase
    exit
  elif [ "$AMOUNT_CONTAINERS_PRESENT" -eq 0 ]
  then
    echo "$SUB_COMMAND there is no sub-advisor-container (stopped or running) present. Please run d-run or d-build." && increase
  elif [ "$AMOUNT_CONTAINERS_PRESENT" -eq 1 ]
  then
    echo "$SUB_COMMAND start container:" && increase
    docker start "$CONTAINER_PRESENT"
  fi
}

stop(){
  if [ $AMOUNT_CONTAINERS_RUNNING -eq 0 ]
  then
    echo "$SUB_COMMAND no sub-advisor-container running." && increase
  else
    echo "$SUB_COMMAND stop all sub-advisor-container" && increase
    docker stop "$CONTAINER_RUNNING"
  fi
}

clean() {
    if [ $CONTAINER_PRESENT ]
    then
      if [ $CONTAINER_RUNNING ]
      then
        echo "$SUB_COMMAND stop all running container" && increase
        docker stop "$CONTAINER_RUNNING"
        echo "$SUB_COMMAND stopped all running container" && increase
      else
        echo "$SUB_COMMAND no running container" && increase
      fi
      echo "$SUB_COMMAND remove all present container" && increase
      docker rm $CONTAINER_PRESENT
      echo "$SUB_COMMAND removed all present container" && increase
    else
      echo "$SUB_COMMAND no sub-advisor-container present"  && increase
    fi

    if [ "$IMAGE_PRESENT" ]
    then
      echo "$SUB_COMMAND remove all images" && increase
      docker rmi "$SUBADVISOR_IMAGE"
      echo "$SUB_COMMAND removed all images" && increase
    else
      echo "$SUB_COMMAND no sub-advisor-image present"  && increase
    fi
}

run() {
  echo "$SUB_COMMAND run container from image" && increase
  docker run -p 8080:8080 -d --name "$SUBADVISOR_CONTAINER" "$SUBADVISOR_IMAGE" & process_id=$!
  wait $process_id
  count_containers_running
  if [ "$(docker ps -q -f name=$SUBADVISOR_CONTAINER)" ]
  then
    echo "$SUB_COMMAND container started sucessfully in detached-mode" && increase
  else
    echo "$SUB_COMMAND Something went wrong." && increase
  fi
}





