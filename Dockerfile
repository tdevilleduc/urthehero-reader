FROM openjdk:11.0.4-jre-slim

ARG JAR_FILE

ENV TZ "/usr/share/zoneinfo/Europe/Paris"
ENV JAVA_OPTIONS "-Duser.home -Dquarkus.profile=docker -Dfile.encoding=UTF8"

ADD ./target/${JAR_FILE} /opt/app.jar

EXPOSE 8080

CMD ["sh", "-c", "java ${JAVA_OPTIONS} -jar /opt/app.jar"]
