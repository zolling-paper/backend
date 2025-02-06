FROM openjdk:17

ENV TZ=Asia/Seoul

COPY build/libs/* app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
