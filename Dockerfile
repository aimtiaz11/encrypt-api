FROM openjdk:8
COPY target/encrypt-api.jar .
ADD secure-properties-tool.jar secure-properties-tool.jar

ENTRYPOINT ["java", "-jar", "-Dencryption.key.dev=$DEVKEY", "-Dencryption.key.test=$TESTKEY", "/encrypt-api.jar"]