FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/crud-postgressql-0.0.1-SNAPSHOT.jar /app/crud-postgressql.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/crud-postgressql.jar"]