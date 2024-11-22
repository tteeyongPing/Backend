FROM openjdk:21-jdk-slim
# 로컬 환경에서 복사된 keystore.p12 파일을 컨테이너로 복사
COPY keystore.p12 /app/resources/keystore.p12
ADD /build/libs/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
