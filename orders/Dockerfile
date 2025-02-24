
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar orders.jar

EXPOSE 8083

CMD ["java", "-jar", "orders.jar"]