FROM openjdk:21-jdk-slim

WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

RUN cp ./target/file-storage-service-0.0.1-SNAPSHOT.jar /app.jar

RUN rm -rf /app

CMD ["java", "-jar", "/app.jar"]

EXPOSE 8082:8082
