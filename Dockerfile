FROM openjdk:21-jdk-slim AS builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .

COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar


FROM openjdk:21-jdk-slim

COPY --from=builder build/libs/*.jar app.jar

ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-Djava.net.preferIPv4Stack=true", "-Djava.net.preferIPv4Addresses=true", "-jar", "-Dspring.profiles.active=local", "/app.jar"]
VOLUME /tmp