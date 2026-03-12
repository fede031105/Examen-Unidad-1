# Etapa 1: Construcción (Build)
# Usamos una imagen de Maven para compilar el código
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos el archivo de configuración y el código fuente
COPY pom.xml .
COPY src ./src

# Compilamos el proyecto y generamos el archivo .jar
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
# Usamos una imagen ligera de Java para correr la aplicación
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto (por defecto Spring usa 8080)
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]