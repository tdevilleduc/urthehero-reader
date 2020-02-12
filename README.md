# URtheHero - Quarkus version

[![Build Status](https://travis-ci.com/tdevilleduc/urthehero-reader.svg)](https://travis-ci.com/tdevilleduc/urthehero-reader)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.tdevilleduc.urthehero%3Aurthehero-reader&metric=coverage)](https://sonarcloud.io/dashboard?id=com.tdevilleduc.urthehero%3Aurthehero-reader)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.tdevilleduc.urthehero%3Aurthehero-reader&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.tdevilleduc.urthehero%3Aurthehero-reader)


```bash
mvn clean install
docker build -t quarkus/reader .
docker build -f src\main\docker\Dockerfile.jvm -t quarkus/reader .
docker-compose up
```
