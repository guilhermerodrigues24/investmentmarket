# Build maven-----------------------
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY src/main ./src/main

COPY pom.xml ./

RUN mvn clean "-Dmaven.test.skip" package

# openJDK runner--------------------
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/projetointegrador*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
