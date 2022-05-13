FROM openjdk:11
MAINTAINER javadovjavad0@gmail.com
WORKDIR /app
ARG JAR_FILE=target/hr-project-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
