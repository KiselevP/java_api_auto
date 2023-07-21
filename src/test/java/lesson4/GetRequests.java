package lesson4;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetRequests extends AbstractTest
{

    @Test
    void websiteResponse()
    {
        given().spec(getRequestSpecification())
                        .when()
                        .get(getBaseUrl() + "recipes/complexSearch")
                        .then()
                        .spec(getResponseSpecification());
    }

    @Test
    void responseValues()
    {
        GetResponse responseInfo =
            given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch").prettyPeek()
                .then()
                .spec(getResponseSpecification())
                .extract()
                .body()
                .as(GetResponse.class);

        assertThat(responseInfo.getOffset(), equalTo(0));
        assertThat(responseInfo.getNumber(), equalTo(10));
        assertThat(responseInfo.getTotalResults(), equalTo(5222));
    }

    /*  а вот так уже более реально  */
    @Test
    void testOne()
    {
        GetResponse responseInfo =
                given().spec(getRequestSpecification())
                        .when()
                        .get(getBaseUrl() + "recipes/complexSearch")
                        .then()
                        .spec(getResponseSpecification())
                        .extract()
                        .body()
                        .as(GetResponse.class);

        List<Integer> listId = new ArrayList<>();

        for (Result result : responseInfo.getResults())
        {
            listId.add(result.getId());
        }

        JsonPath responseJSON =
                given().spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();

        for(int i = 0; i < listId.size(); i++)
        {
            assertThat(listId.get(i),
                    equalTo(responseJSON.getList("results.id").get(i)));
        }
    }

    @Test
    void testTwo()
    {
        given().spec(getRequestSpecification())
                .expect()
                .body("results.id", contains(782585, 716426, 715497, 715415, 716406, 644387, 715446, 782601, 795751, 766453))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification());
    }
}
