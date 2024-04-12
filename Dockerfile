FROM adoptopenjdk:17-jdk-hotspot AS builder

WORKDIR /home/gradle/project
COPY . .
RUN ./gradlew clean build --no-daemon

FROM adoptopenjdk:17-jre-hotspot

COPY --from=builder /home/gradle/project/build/libs/board-0.0.1-SNAPSHOT.jar /app/board.jar

CMD ["java", "-jar", "/app/board.jar"]
