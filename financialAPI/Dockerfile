# Etapa 1: Build do aplicativo
FROM maven:3.9.9-ibm-semeru-21-jammy AS build
WORKDIR /financialAPI

# Copiar o código fonte e o arquivo pom.xml para o container
COPY ./pom.xml ./
COPY ./src ./src

# Executar o Maven para construir o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Executar o aplicativo
FROM openjdk:22-jdk-slim
WORKDIR /app

# Copiar o JAR gerado para o diretório final
COPY --from=build /financialAPI/target/finacialAPI.jar /app/finacialAPI.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/finacialAPI.jar"]
