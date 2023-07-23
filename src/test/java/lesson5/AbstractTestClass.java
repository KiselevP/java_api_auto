package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.CategoryService;
import lesson5.api.ProductService;
import lesson5.dto.ProductDto;
import lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;

public class AbstractTestClass {
    private static CategoryService categoryService;
    private static ProductService productService;
    private static ProductDto product;
    private static Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        categoryService =
                RetrofitUtils.getRetrofit().create(CategoryService.class);

        productService =
                RetrofitUtils.getRetrofit().create(ProductService.class);

        product = new ProductDto()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public static ProductDto getProduct() {
        return product;
    }
}
