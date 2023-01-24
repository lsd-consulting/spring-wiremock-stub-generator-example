#!/usr/bin/env sh

set -e

cd java-spring-wiremock-stub-generator-example-server
./gradlew clean build publishToMavenLocal
cd ..

cd kotlin-spring-wiremock-stub-generator-example-server
./gradlew clean build publishToMavenLocal
cd ..

cd spring-wiremock-stub-generator-example-client
./gradlew clean build
