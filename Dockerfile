FROM openjdk:11
EXPOSE 8089
ADD target/events-project.jar events-project.jar
ENTRYPOINT ["java", "-jar", "/events-project.jar"]