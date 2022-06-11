FROM openjdk:8u191-jre-alpine3.8
COPY target/*.jar query.jar
ENTRYPOINT ["java","-jar","/query.jar"] 