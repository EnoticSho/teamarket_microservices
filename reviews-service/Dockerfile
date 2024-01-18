## Используем образ с Gradle и JDK для сборки проекта
#FROM gradle:jdk17 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon
#
## Используем другой образ для выполнения собранного приложения
#FROM openjdk:17-slim
#EXPOSE 8080
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]

FROM openjdk:17-slim
ADD build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
