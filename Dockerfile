FROM openjdk:21
LABEL authors="ali.ghareeb"
ENV BACKENDPRACTIVE_DB = ./EmployeesDB.json
ARG JAR_FILE=target/*.jar
COPY ./target/BackendPractical-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/keystore.jks keystore.jks
RUN touch EmployeesDB.json
EXPOSE 8443
ENTRYPOINT ["java", "-jar","/app.jar"]