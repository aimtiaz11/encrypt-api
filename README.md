# secure-properties Encryption API

## About

Springboot app providing REST-API to allow properties to be encrypted via MuleSoft's secure-properties.jar without
caller knowing the key.

Refer to this link to learn more about secuire-properties configuration JAR:
https://docs.mulesoft.com/mule-runtime/4.4/secure-configuration-properties

Requires Java 8.

## Running standalone JAR

After cloning this repo, run via Maven:

```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dencryption.key.nonprod=secret1 -Dencryption.key.prod=secret2"
```
or via `java -jar` command after doing mvn package first:

```
java -jar -Dencryption.key.nonprod=secret1 -Dencryption.key.prod=secret2 target/encrypt-api.jar
```

## Running via Docker

After cloning and running `mvn package`:

Step 1: Build image

```
docker build -t aimtiaz11/encrypt-api .
```

Step 2: Run docker container image
```
docker run -p 8080:8080 -e "PROD=secret1;NON_PROD=secret2" aimtiaz11/encrypt-api
```

## Encrypting value via REST-API
Following is a sample Curl command on how to call the API.
```
curl --location --request POST 'http://localhost:8080/encryption-api/api/v1/encrypt' \
--header 'Content-Type: application/json' \
--data-raw '{
    "environment": "NONPROD",
    "value": "testvalue"
}'
```
environment can be `PROD` or `NONPROD`

## swagger-ui
You can also use the API via Swagger UI located in: http://localhost:8080/swagger-ui/


## License MIT
(The MIT License)

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.