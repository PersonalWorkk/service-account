FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ARG JAR_FILE=build/libs/service-account-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} service-account.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/service-account-0.0.1-SNAPSHOT.jar"]