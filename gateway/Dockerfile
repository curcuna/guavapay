FROM openjdk:17-jdk-slim
COPY build/libs/*.jar app.jar
ENV AUTHENTICATION_SERVICE $AUTHENTICATION_SERVICE
ENV ORDER_SERVICE $ORDER_SERVICE
ENV CARGO_SERVICE $CARGO_SERVICE
CMD java -jar /app.jar