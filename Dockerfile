FROM openjdk:17-jdk-alpine AS builder

WORKDIR /home/gradle/project
COPY . .

RUN mkdir -p /root/.gradle && \
    echo "systemProp.http.proxyHost=$HTTP_PROXY_HOST" >> /root/.gradle/gradle.properties && \
    echo "systemProp.http.proxyPort=$HTTP_PROXY_PORT" >> /root/.gradle/gradle.properties && \
    echo "systemProp.https.proxyHost=$HTTPS_PROXY_HOST" >> /root/.gradle/gradle.properties && \
    echo "systemProp.https.proxyPort=$HTTPS_PROXY_PORT" >> /root/.gradle/gradle.properties


RUN chmod +x ./gradlew && ./gradlew clean build --no-daemon

FROM openjdk:17-jdk-alpine

COPY --from=builder /home/gradle/project/build/libs/board-0.0.1-SNAPSHOT.jar /app/board.jar

CMD ["java", "-jar", "/app/board.jar"]
