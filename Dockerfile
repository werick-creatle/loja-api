# Etapa 1: Build da aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o pom.xml e baixa dependências (cache ajuda no build)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código total e builda o jar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime da aplicação (leve e otimizado)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Porta padrão da aplicação Spring Boot
EXPOSE 8080

# Comando para rodar a API
ENTRYPOINT ["java", "-jar", "app.jar"]
