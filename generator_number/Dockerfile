FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar gen_app.jar

EXPOSE 8082

CMD ["java", "-jar", "gen_app.jar"]