package lesson6;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductsTests extends AbstractTestClass {
    @SneakyThrows
    @Test
    void checkProductsByTitleTest() {
        getCategoriesExample().createCriteria().andTitleLike("Electronic");
        List<model.Categories> list = getCategoriesMapper().selectByExample(getCategoriesExample());

        Assertions.assertNotNull(list);
    }

    @SneakyThrows
    @Test
    void checkCountProducts() {
        List<model.Products> list = getProductsMapper().selectByExample(getProductsExample());
        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(), 5);
    }

    @SneakyThrows
    @Test
    void checkCountProductsByCategory() {
        getProductsExample().createCriteria().andCategory_idEqualTo(1L);
        List<model.Products> list = getProductsMapper().selectByExample(getProductsExample());
        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(), 3);
    }

    @SneakyThrows
    @Test
    void addProduct() {
        model.Products products = new model.Products();
        products.setTitle("Tesla phone");
        products.setCategory_id(2L);
        getProductsMapper().insert(products);
        getSession().commit();

        List<model.Products> list = getProductsMapper().selectByExample(getProductsExample());
        Assertions.assertEquals(list.size(), 6);
    }

    @SneakyThrows
    @Test
    void modifyProduct() {
        getProductsExample().createCriteria().andTitleLike("Tesla phone");
        List<model.Products> list = getProductsMapper().selectByExample(getProductsExample());
        model.Products upgradeProduct = list.get(0);
        upgradeProduct.setTitle("Tesla phone 2");
        getProductsMapper().updateByPrimaryKey(upgradeProduct);
        getSession().commit();

        model.ProductsExample productsExample = new model.ProductsExample();
        productsExample.createCriteria().andTitleLike("Tesla phone 2");
        List<model.Products> resList = getProductsMapper().selectByExample(productsExample);
        Assertions.assertNotNull(resList);
    }

    @SneakyThrows
    @Test
    void deleteProduct() {
        getProductsExample().createCriteria().andTitleLike("Tesla phone 2");
        getProductsMapper().deleteByExample(getProductsExample());
        getSession().commit();

        model.ProductsExample productsExample = new model.ProductsExample();
        List<model.Products> resList = getProductsMapper().selectByExample(productsExample);
        Assertions.assertEquals(resList.size(), 5);
    }
}
