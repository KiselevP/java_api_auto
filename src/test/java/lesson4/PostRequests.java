package lesson4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostRequests extends AbstractTest
{
    @Test
    void test1()
    {
        given().spec(getRequestSpecification())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void test3()
    {
        String request = given()
                .spec(getRequestSpecification())
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .jsonPath().toString();

        assertThat(request, notNullValue());
}

    @Test
    void test4()
    {
        given().spec(getRequestSpecification())
                .expect()
                .body("cuisine", equalTo("Italian"))
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void test5()
    {
        given().spec(getRequestSpecification())
                .expect()
                .body("confidence", equalTo(0.0F))
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(getResponseSpecification());
    }

    @Test
    void addMealTest()
    {
        String responseID =
                given().spec(getRequestSpecification())
                        .queryParam("hash", getHash())
                        .pathParams("user_name", getUserName())
                        .body("{\n"
                                + " \"item\": \"5 package sugar\",\n"
                                + " \"aisle\": \"Baking\",\n"
                                + " \"parse\": true\n"
                                + "}"
                        )
                        .log().all()
                        .when()
                        .post(getBaseUrl() + "mealplanner/{user_name}/shopping-list/items")
                        .prettyPeek()
                        .jsonPath()
                        .get("id")
                        .toString();

        given().spec(getRequestSpecification())
                .pathParams("user_name", getUserName())
                .queryParam("hash", getHash())
                .pathParams("id", responseID)
                .log().all()
                .when()
                .delete(getBaseUrl() + "mealplanner/{user_name}/shopping-list/items/{id}")
                .prettyPeek()
                .then()
                .spec(getResponseSpecification());


    }

    @Test
    void checkDeleteElem()
    {
        given().spec(getRequestSpecification())
                .queryParam("hash", getHash())
                .pathParams("user_name", getUserName())
                .expect()
                .body("cost", equalTo(0.0F))
                .log().all()
                .when()
                .get(getBaseUrl() + "mealplanner/{user_name}/shopping-list")
                .prettyPeek()
                .then()
                .spec(getResponseSpecification());
    }
}
