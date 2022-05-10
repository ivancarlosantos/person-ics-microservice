FROM maven:3.8.5-openjdk-11-slim AS build 

COPY /src /app/src

COPY /pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:11

LABEL key="core.ics.api"

WORKDIR /usr/src/app

COPY --from=build /app/target/*.jar person-ics-microservice.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "person-ics-microservice.jar"]