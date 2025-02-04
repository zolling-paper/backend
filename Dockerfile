FROM --platform=linux/x86 amazoncorretto:17

ENV TZ=Asia/Seoul

COPY backend/build/libs/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
