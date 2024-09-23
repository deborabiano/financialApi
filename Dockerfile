# Etapa 1: Build do aplicativo
FROM maven:3.9.9-ibm-semeru-21-jammy AS build
WORKDIR /financialAPI/financialAPI/

# Copiar o arquivo pom.xml e instalar as dependências
COPY main .
COPY ./pom.xml ./
RUN mvn dependency:go-offline

# Copiar o restante dos arquivos do projeto e compilar o aplicativo
COPY ./src/main/java/ ./src/test/java/
RUN mvn clean package -DskipTests

# Etapa 2: Criação da imagem final com JDK 22
FROM eclipse-temurin:22-jdk-alpine

# Criar um diretório para o aplicativo
WORKDIR /app

# Copiar o arquivo JAR do build anterior
COPY --from=build /app/target/finacilaAPI.jar .

# Expor a porta que o serviço usará
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "finacialAPI.jar"]
