FROM openjdk:8-alpine
MAINTAINER Nicow (nicobod@gmail.com)

#after mvn package
#ADD microservice-web/target/microservice-web-1.0.2.jar microservice-web-1.0.2.jar

#without mvn package
ADD /executable/microservice-web-1.0.2.jar microservice-web-1.0.2.jar

EXPOSE 9001
ENTRYPOINT ["java","-jar","microservice-web-1.0.2.jar"]
