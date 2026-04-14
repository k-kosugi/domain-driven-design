FROM eclipse-temurin:25-jre
WORKDIR /app
COPY catalog/target/catalog-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
