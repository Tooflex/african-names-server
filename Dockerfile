FROM gradle:7.3.0-jdk11-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk:11-jdk-hotspot
MAINTAINER Otourou Da Costa <otouroudacosta@gmail.com>
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/prenomsafricains.jar
ENTRYPOINT ["java","-jar","/app/prenomsafricains.jar"]
