FROM openjdk:17
COPY main_api/build/libs/main_api-1.0.jar file-api.jar
ENTRYPOINT ["nohup", "java", "-jar", "-Dspring.profiles.active=dev", "file-api.jar", "&"]
