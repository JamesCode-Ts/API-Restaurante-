# Stage 1: Build the application
FROM ubuntu:latest AS build

# Atualiza a lista de pacotes e instala o JDK
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Configura o diretório de trabalho e copia os arquivos
WORKDIR /app
COPY . .

# Instala o Maven e constrói o aplicativo
RUN apt-get install -y maven
RUN mvn clean install

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Configura a exposição da porta
EXPOSE 8080

# Configura o diretório de trabalho e copia o arquivo JAR do estágio anterior
WORKDIR /app
COPY --from=build /app/target/restaurante-0.0.1-SNAPSHOT.jar app.jar

# Configura o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
