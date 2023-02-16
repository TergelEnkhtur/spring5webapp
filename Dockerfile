#
# Build stage
#
FROM maven:3.8.6-jdk-8 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:8-jdk-slim
COPY --from=build /target/spring5webapp-0.0.1-SNAPSHOT.jar spring5webapp.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring5webapp.jar"]