# MuleSoft Encryption API

## Running standalone jar

Run via Maven:
```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dencryption.key.nonprod=secret1 -Dencryption.key.prod=secret2"
```
or via `java -jar`:

```
java -jar -Dencryption.key.nonprod=secret1 -Dencryption.key.prod=secret2 target/encrypt-api.jar
```

## Running via Docker

# Build Image
```
docker build -t aimtiaz11/encrypt-api .
```
## Run Image

```
docker run -p 8080:8080 -e "PROD=secret1;NON_PROD=secret2" aimtiaz11/encrypt-api
```

## Encrypting value
Run the following command to encrypt:

```
curl --location --request POST 'http://localhost:8080/encryption-api/api/v1/encrypt' \
--header 'Content-Type: application/json' \
--data-raw '{
    "environment": "NONPROD",
    "value": "testvalue"
}'
```
environment can be `PROD` or `NONPROD`