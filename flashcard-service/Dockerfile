FROM maven:3.6.3-adoptopenjdk-8
COPY pom.xml pom.xml
COPY src/* src/
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/*.jar"]