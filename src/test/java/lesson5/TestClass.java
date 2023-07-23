package lesson5;

import lesson5.dto.CategoryDto;
import lesson5.dto.ProductDto;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestClass extends AbstractTestClass {
    @SneakyThrows
    @Test
    void getCategoryByIdTest() {
        Response<CategoryDto> response = getCategoryService().getCategory(1).execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(1));
        assertThat(response.body().getTitle(), equalTo("Food"));
    }

    @SneakyThrows
    @Test
    void createProductTest() {
        Response<ProductDto> response = getProductService().createProduct(getProduct())
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getProductByIdTest() {
        Response<ProductDto> response = getProductService().getProductById(1)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getAllProductsTest() {
        Response<ResponseBody> response = getProductService().getProducts()
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    /*не понял, что с ним делать*/
    @SneakyThrows
    @Test
    void modifyProductTest() {
        Response<ProductDto> response = getProductService().modifyProduct(getProduct())
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void deleteProductTest() {
        Response<ResponseBody> response = getProductService().deleteProduct(1)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
