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
mvn clean package -DskipTests -Pnative -Dquarkus.native.container-runtime=docker -Dquarkus.native.native-image-xmx=16g
```

> Don't forget to move your Docker usage Memory to 16Go

### Build Docker image
```
docker build -f src/main/docker/Dockerfile.native -t docker.io/thomasdevilleduc/urthehero-reader .
```

### Run Docker image
```
docker run -i --rm -p 8080:8080 docker.io/thomasdevilleduc/urthehero-reader
```
