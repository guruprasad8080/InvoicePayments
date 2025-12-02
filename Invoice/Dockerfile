FROM openjdk:17-jdk-slim
LABEL maintainer="guru8080"
WORKDIR /ivPayService
COPY target/Invoice-0.0.1-SNAPSHOT.jar ivPayService.jar
EXPOSE 8080
CMD ["java","-jar","ivPayService.jar"]