FROM amazoncorretto:17

LABEL maintainer="sopiaah100@gmail.com"

EXPOSE 8080

VOLUME /tmp

ADD target/starwars-graphql-service-0.0.1-SNAPSHOT.jar starwars-graphql-service.jar

RUN /bin/sh -c 'touch /starwars-graphql-service.jar'

ENV TZ=Africa/Nairobi

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENTRYPOINT ["java","-Xmx256m", "-XX:+UseG1GC", "-Djava.security.egd=file:/dev/./urandom","-jar","/starwars-graphql-service.jar"]
