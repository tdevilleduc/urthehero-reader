quarkus-test

```bash
mvn clean install
docker build -t quarkus/reader .
docker build -f src\main\docker\Dockerfile.jvm -t quarkus/reader .
docker-compose up
```
