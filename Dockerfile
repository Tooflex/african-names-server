FROM adoptopenjdk:11-jdk-hotspot
MAINTAINER Otourou Da Costa <otouroudacosta@gmail.com>
EXPOSE 8080
ARG JAR_FILE=build/libs/prenomsafricains-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
