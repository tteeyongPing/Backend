FROM openjdk:21-jdk-slim
ADD /build/libs/*.jar app.jar
COPY /etc/letsencrypt/live/newsee.xyz/keystore.p12 /app/resources/keystore.p12 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
