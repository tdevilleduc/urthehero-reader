# URtheHero - Quarkus version

[![Build Status](https://travis-ci.org/tdevilleduc/urthehero-reader.svg)](https://travis-ci.org/tdevilleduc/urthehero-reader)


```bash
mvn clean install
docker build -t quarkus/reader .
docker build -f src\main\docker\Dockerfile.jvm -t quarkus/reader .
docker-compose up
```
