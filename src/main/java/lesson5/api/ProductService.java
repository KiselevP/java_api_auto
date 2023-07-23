package lesson5.api;

import lesson5.dto.ProductDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {

    @POST("products")
    Call<ProductDto> createProduct(@Body ProductDto createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("products")
    Call<ProductDto> modifyProduct(@Body ProductDto modifyProductRequest);

    @GET("products/{id}")
    Call<ProductDto> getProductById(@Path("id") int id);

    @GET("products")
    Call<ResponseBody> getProducts();

}
