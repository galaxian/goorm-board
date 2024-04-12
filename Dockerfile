FROM openjdk:17-jdk-alpine AS builder

WORKDIR /home/gradle/project
COPY . .

RUN mkdir -p /root/.gradle && echo -e "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" > /root/.gradle/gradle.properties

RUN chmod +x ./gradlew && ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-alpine

COPY --from=builder /home/gradle/project/build/libs/board-0.0.1-SNAPSHOT.jar /app/board.jar

CMD ["java", "-jar", "/app/board.jar"]
