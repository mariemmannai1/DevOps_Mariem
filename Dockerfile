FROM openjdk:11
EXPOSE 8089
ADD /target/eventsproject-1.0.jar eventsproject.jar
ENTRYPOINT ["java", "-jar", "eventsproject.jar"]