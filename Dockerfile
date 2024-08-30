FROM openjdk:17-oracle
EXPOSE 8086
ADD target/social-media-2024-v1.jar social-media-2024-v1.jar
ENTRYPOINT ["java","-jar","social-media-2024-v1.jar"]