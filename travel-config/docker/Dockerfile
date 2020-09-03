FROM openjdk:8-jre

ENV APP_VERSION 1.0.0-SNAPSHOT

RUN mkdir /app

COPY travel-config-$APP_VERSION.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar","--spring.profiles.active=dev"]

EXPOSE 8888
