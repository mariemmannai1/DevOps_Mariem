FROM openjdk:11
COPY target/*.jar /
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/eventsproject.jar"]