package lesson6;

import lombok.SneakyThrows;
import model.CategoriesExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CategoriesTests extends AbstractTestClass
{
    @SneakyThrows
    @Test
    void checkCategoryByTitleTest()
    {
        getCategoriesExample().createCriteria().andTitleLike("Electronic");
        List<model.Categories> list = getCategoriesMapper().selectByExample(getCategoriesExample());

        Assertions.assertNotNull(list);
    }

    @SneakyThrows
    @Test
    void checkCountCategory()
    {
        List<model.Categories> list = getCategoriesMapper().selectByExample(getCategoriesExample());
        Assertions.assertEquals(list.size(), 2);
    }

    @SneakyThrows
    @Test
    void addCategory()
    {
        model.Categories categories = new model.Categories();
        categories.setTitle("Fertilizer");
        getCategoriesMapper().insert(categories);
        getSession().commit();

        List<model.Categories> list = getCategoriesMapper().selectByExample(getCategoriesExample());
        Assertions.assertEquals(list.size(), 3);
    }

    @SneakyThrows
    @Test
    void modifyCategory()
    {
        getCategoriesExample().createCriteria().andTitleLike("Fertilizer");
        List<model.Categories> list = getCategoriesMapper().selectByExample(getCategoriesExample());
        model.Categories upgradeCategory = list.get(0);
        upgradeCategory.setTitle("Medicines");
        getCategoriesMapper().updateByPrimaryKey(upgradeCategory);
        getSession().commit();

        model.CategoriesExample categoriesExample = new CategoriesExample();
        categoriesExample.createCriteria().andTitleLike("Medicines");
        List<model.Categories> resList = getCategoriesMapper().selectByExample(categoriesExample);
        Assertions.assertNotNull(resList);
    }

    @SneakyThrows
    @Test
    void deleteCategory()
    {
        getCategoriesExample().createCriteria().andTitleLike("Medicines");
        getCategoriesMapper().deleteByExample(getCategoriesExample());
        getSession().commit();

        model.CategoriesExample categoriesExample = new CategoriesExample();
        List<model.Categories> resList = getCategoriesMapper().selectByExample(categoriesExample);
        Assertions.assertEquals(resList.size(), 2);
    }
}
