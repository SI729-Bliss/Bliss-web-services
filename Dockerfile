# Etapa de construcción
FROM ubuntu:latest AS build

# Actualizar los paquetes e instalar Java y Maven Wrapper
RUN apt-get update && apt-get install -y openjdk-21-jdk git curl

# Establece la variable de entorno para Java
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Copiar el código fuente del proyecto al contenedor
WORKDIR /app
COPY . .

# Otorgar permisos de ejecución al Maven Wrapper
RUN chmod +x ./mvnw

# Construir la aplicación sin ejecutar pruebas
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM openjdk:21-jdk-slim

# Expone el puerto en el que la aplicación Spring Boot corre por defecto
EXPOSE 8080

# Copiar el archivo JAR de la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
