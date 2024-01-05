FROM eclipse-temurin:17 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY setting.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradle bootJar


FROM eclipse-temurin:17
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
LABEL authors="deaho"

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]