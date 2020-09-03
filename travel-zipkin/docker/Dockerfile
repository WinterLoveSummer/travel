FROM openjdk:8-jre

ENV APP_VERSION 1.0.0-SNAPSHOT
ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app
COPY travel-zipkin-$APP_VERSION.jar /app/app.jar

ENTRYPOINT ["dockerize","-timeout","5m", "-wait", "http://192.168.219.129:8888/travel-zipkin/dev/dev","java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar","--spring.profiles.active=dev"]

EXPOSE 9411
