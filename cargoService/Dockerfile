FROM openjdk:17-jdk-slim
COPY build/libs/*.jar app.jar
ENV POSTGRES $POSTGRES
ENV RABBITMQ $RABBITMQ
CMD java -jar /app.jar