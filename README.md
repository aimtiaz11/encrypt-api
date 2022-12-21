# secure-properties Encryption API

## About

Springboot app providing REST-API to allow properties to be encrypted via MuleSoft's secure-properties.jar without
caller knowing the key.

Refer to this link to learn more about secuire-properties configuration JAR:
https://docs.mulesoft.com/mule-runtime/4.4/secure-configuration-properties

Requires Java 8.

You would first need to work out how you want to setup your encryption key per environment.
For example, you can have separate encryption key per environment or have prod vs nonprod key.
This app will cater for for all cases.

The application requires each key to be passed in this format:

`-Dencryption.key.<envSuffix>=<encryption_key>`

For example, if you have separate keys for your dev, test, and prod environment, the parameters
would look like:

```
-Dencryption.key.dev=dev_secret -Dencryption.key.test=test_secret -Dencryption.key.production=prod_secret
```


## Running standalone JAR

After cloning this repo, run via Maven:

```
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dencryption.key.dev=dev_secret -Dencryption.key.test=test_secret"
```
or via `java -jar` command after doing mvn package first:

```
java -jar -Dencryption.key.dev=dev_secret -Dencryption.key.test=test_secret target/encrypt-api.jar
```

## Running via Docker


**Step 1**: Clone the repository and run:

```
mvn clean package
```

**Step 2**: Update `Dockerfile` ENTRYPOINT with list of Java system properties for your environment.
Refer to above examples for the the convention.

For 2 environment, DEV and TEST it would look like below:
```
ENTRYPOINT ["java", "-jar", "-Dencryption.key.dev=$DEVKEY", "-Dencryption.key.test=$TESTKEY", "/encrypt-api.jar"]
```

**Step 2**: Update the `env.list` file to with encryption keys

```
DEVKEY=foobar1
TESTKEY=foobar2
```

**Step 3**: Build image

```
docker build -t aimtiaz11/encrypt-api .
```


**Step 4**: Run docker container image
```
docker run --env-file env.list aimtiaz11/encrypt-api
```

## Encrypting value via REST-API

Following is a sample Curl command on how to call the API.

```
curl --location --request POST 'http://localhost:8080/encryption-api/api/v1/encrypt' \
--header 'Content-Type: application/json' \
--data-raw '{
    "environment": "dev",
    "value": "testvalue"
}'
```

## swagger-ui
You can also use the API via Swagger UI located in: http://localhost:8080/swagger-ui/


## License MIT
(The MIT License)

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.