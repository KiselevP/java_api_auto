package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest
{
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apiKey;
    private static String baseUrl;
    private static String hash;
    private static String userName;
    private static String password;
    private static ResponseSpecification responseSpecification;
    private static RequestSpecification requestSpecification;

    @BeforeAll
    static void initTest()
            throws IOException
    {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_url");
        hash = prop.getProperty("hash");
        userName = prop.getProperty("user_name");
        password = prop.getProperty("spoonacularPassword");

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getHash() {
        return hash;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static ResponseSpecification getResponseSpecification() {
        return responseSpecification;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
