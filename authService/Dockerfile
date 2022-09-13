FROM openjdk:17-jdk-slim
COPY build/libs/*.jar app.jar
ENV POSTGRES $POSTGRES
CMD java -jar /app.jar