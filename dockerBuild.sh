#!/bin/bash

./gradlew build

docker build --build-arg JAR_FILE=build/libs/\api-0.0.1-SNAPSHOT.jar -t izitktj/api-rinha .

docker-compose up