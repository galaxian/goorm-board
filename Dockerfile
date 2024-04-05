FROM openjdk:17-alpine

WORKDIR /home/gradle/project

COPY . .

RUN chmod +x ./gradlew && ./gradlew clean build

CMD ["java", "-jar", "/home/gradle/project/build/libs/board-0.0.1-SNAPSHOT-plain.jar"]
