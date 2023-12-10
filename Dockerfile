FROM openjdk:11
EXPOSE 8089
ADD /target/eventsproject.jar eventsproject.jar
ENTRYPOINT ["java", "-jar", "eventsproject.jar"]