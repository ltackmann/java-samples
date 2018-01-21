FROM openjdk:8
COPY build/libs/sandbox-0.1.0.jar /sandbox-app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/sandbox-app.jar"]