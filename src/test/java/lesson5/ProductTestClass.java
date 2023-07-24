package lesson5;

import lesson5.dto.ProductDto;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProductTestClass extends AbstractTestClass {

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

    @SneakyThrows
    @Test
    void modifyProductTest() {
        Response<ProductDto> response = getProductService().modifyProduct(getProduct().withId(1))
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
