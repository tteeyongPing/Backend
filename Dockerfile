FROM openjdk:17-jdk-slim
# JAR 파일 추가
ADD /build/libs/*.jar app.jar
# Spring Boot 애플리케이션 실행
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
