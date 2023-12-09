FROM openjdk:11
EXPOSE 8089
ADD /target/events-1.0.jar events-1.0.jar
ENTRYPOINT ["java", "-jar", "events-1.0.jar"]