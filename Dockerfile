FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build

COPY build/libs/*.jar /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]