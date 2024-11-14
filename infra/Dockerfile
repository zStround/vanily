FROM openjdk:21-jdk

WORKDIR /app

COPY /build/libs/infra-1.0.jar /app/infra-1.0.jar

EXPOSE 8000

CMD ["java", "-jar", "/app/infra-1.0.jar"]