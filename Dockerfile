FROM openjdk:17-jdk-alpine AS builder

WORKDIR /home/gradle/project
COPY . .
RUN ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-alpine

COPY --from=builder /home/gradle/project/build/libs/board-0.0.1-SNAPSHOT.jar /app/board.jar

CMD ["java", "-jar", "/app/board.jar"]
