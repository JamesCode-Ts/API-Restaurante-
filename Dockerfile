# Stage 1: Build the application
FROM maven:3.8.6 AS build

# Configura o diretório de trabalho
WORKDIR /app

# Copia os arquivos da aplicação
COPY . .

# Instala o Maven e constrói o aplicativo
RUN mvn clean install

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Configura a exposição da porta
EXPOSE 8080

# Copia o arquivo JAR do estágio anterior
COPY --from=build /app/target/restaurante-0.0.1-SNAPSHOT.jar app.jar

# Configura o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
