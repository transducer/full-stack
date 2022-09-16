FROM openjdk:8-alpine

COPY target/default+uberjar/software.rooijakkers.full-stack.jar /software.rooijakkers.full-stack/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/software.rooijakkers.full-stack/app.jar"]
