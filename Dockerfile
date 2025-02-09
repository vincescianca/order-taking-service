# Fase 1: Build del progetto usando Maven
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Directory di lavoro nel container
WORKDIR /app

# Copia il file pom.xml e scarica le dipendenze (per sfruttare la cache di Maven)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia il codice sorgente
COPY src ./src

# Compilazione e pacchetto applicazione. Skippa i test per velocizzare.
RUN mvn clean package -DskipTests

# Fase 2: Immagine finale con JRE leggero
FROM eclipse-temurin:21-jre

# Directory di lavoro nel container
WORKDIR /app

# Copia il file JAR dalla fase di build
COPY --from=builder /app/target/*.jar app.jar

# Esposizione della porta 8080
EXPOSE 8080

# Comando per avviare l'applicazione
CMD ["java", "-jar", "app.jar"]