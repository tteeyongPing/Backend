FROM openjdk:21-jdk-slim
# 로컬 빌드 환경에서 Docker 이미지에 keystore.p12 파일 복사
COPY keystore.p12 /app/resources/keystore.p12
ADD /build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
