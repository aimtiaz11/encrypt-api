FROM openjdk:19

COPY target/encrypt-api.jar .
ADD secure-properties-tool.jar secure-properties-tool.jar
ENTRYPOINT ["java", "-jar", "-Dencryption.key.nonprod=$NON_PROD", "-Dencryption.key.prod=$PROD", "/encrypt-api.jar"]
