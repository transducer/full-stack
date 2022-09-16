FROM openjdk:8-alpine
MAINTAINER Erwin Rooijakkers <erwin@rooijakkers.software>

ADD target/software.rooijakkers.full-stack-0.0.1-SNAPSHOT-standalone.jar /software.rooijakkers.full-stack/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/software.rooijakkers.full-stack/app.jar"]
