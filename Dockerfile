FROM openjdk:21-jdk-slim

# Docker 빌드 환경에서 keystore.p12 파일을 resources 폴더로 복사
COPY keystore.p12 /app/resources/keystore.p12

# JAR 파일 추가
ADD /build/libs/*.jar app.jar

# Spring Boot 애플리케이션 실행
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
