#inicia con la imagen base que contiene Java runtime
FROM openjdk:17-jdk-slim as build
LABEL authors="luis-avila"
# se agregar el jar del microservicio al contenedor
COPY target/qph-backend-0.0.1-SNAPSHOT.jar qph-backend-0.0.1-SNAPSHOT.jar
#se ejecuta el microservicio
ENTRYPOINT ["java","-jar","/qph-backend-0.0.1-SNAPSHOT.jar"]