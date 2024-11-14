FROM openjdk:21-jdk

WORKDIR /app

COPY /build/libs/currencies-service-1.0.jar /app/currencies-service-1.0.jar

EXPOSE 8002

CMD ["java", "-jar", "/app/currencies-service-1.0.jar"]