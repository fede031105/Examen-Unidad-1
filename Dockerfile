# Etapa 1: Construcción (Build) usando Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos todo el contenido del proyecto
COPY . .

# Compilamos saltando las pruebas para que sea más rápido
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run) usando Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiamos el archivo .jar generado
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto estándar
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]