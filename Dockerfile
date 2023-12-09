FROM openjdk:11
EXPOSE 8089
ADD /target/events-1.0.jar events.jar
ENTRYPOINT ["java", "-jar", "events.jar"]