# URtheHero - Quarkus version

[![Build Status](https://travis-ci.com/tdevilleduc/urthehero-reader.svg)](https://travis-ci.com/tdevilleduc/urthehero-reader)


```bash
mvn clean install
docker build -t quarkus/reader .
docker build -f src\main\docker\Dockerfile.jvm -t quarkus/reader .
docker-compose up
```

### Build native image
```
mvn clean package -DskipTests -Pnative -Dquarkus.native.container-runtime=docker -Dquarkus.profile=docker
```

> Don't forget to move your Docker usage Memory to 16Go

### Build Docker image
```
docker build -f src/main/docker/Dockerfile.native -t urthehero-reader .
```

### Run Docker image
```
docker run -p 8080:8080 urthehero-reader:latest
```
