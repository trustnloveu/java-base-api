FROM openjdk:17
COPY main-api/build/libs/main-api-1.0.jar file-api.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "file-api.jar"]
