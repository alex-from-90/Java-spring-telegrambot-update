FROM amazoncorretto:21-alpine-jdk

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR файл в контейнер
COPY target/*.jar app.jar

# Определяем команду для запуска JAR файла
CMD ["java", "-jar", "app.jar"]