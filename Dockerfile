FROM openjdk:17-alpine

WORKDIR /home/gradle/project

COPY . .

RUN chmod +x ./gradlew clean build

ENV DATABASE_URL=jdbc:mariadb://mariadb/board

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/home/gradle/project/build/libs/board-0.0.1-SNAPSHOT-plain.jar"]
