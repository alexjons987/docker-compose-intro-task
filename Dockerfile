FROM eclipse-temurin:21-jdk-alpine
LABEL authors="alexjons987"
WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080