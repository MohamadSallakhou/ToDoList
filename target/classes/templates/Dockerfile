# Verwende ein offizielles JDK-Bild als Basis
FROM openjdk:17-jdk-slim

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere das JAR-File in das Container-Verzeichnis
COPY target/todo-application.jar app.jar

# Exponiere den Port, auf dem die Anwendung läuft (Standardmäßig ist es 8080)
EXPOSE 8080

# Führe die Anwendung aus
ENTRYPOINT ["java", "-jar", "app.jar"]
