import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetRequests extends AbstractTest
{

    @Test
    void websiteResponse()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void responseTime()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .time(lessThan(400L), TimeUnit.MILLISECONDS);
    }

    @Test
    void offsetValue()
    {
        JsonPath response =  given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), is(0));
    }

    @Test
    void numberValue()
    {
        JsonPath response =  given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("number"), is(10));
    }

    @Test
    void totalResultsValue()
    {
        JsonPath response =  given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("totalResults"), is(5222));
    }

    /*  бред конечно(в данной ситуации),
        но он может быть актуален,
        если у нас есть данные, по которым мы будем проверять*/
    @Test
    void testOne()
    {
        JsonPath responseJSON =  given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();

        List<Integer> listIdFromJson = responseJSON.getList("results.id");

        for(int i = 0; i < listIdFromJson.size(); i++)
        {
            assertThat(listIdFromJson.get(i),
                    equalTo(responseJSON.getList("results.id").get(i)));
        }
    }

    @Test
    void testTwo()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .expect()
                .body("results.id", contains(782585, 716426, 715497, 715415, 716406, 644387, 715446, 782601, 795751, 766453))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
    }
}
