FROM openjdk:11-jdk-slim
ADD build/libs/backend-0.0.1-SNAPSHOT.jar /opt/backend/backend-0.0.1-SNAPSHOT.jar
WORKDIR /opt/backend
CMD java -jar backend-0.0.1-SNAPSHOT.jar