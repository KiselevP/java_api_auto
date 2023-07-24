package lesson6;

import dao.CategoriesMapper;
import dao.ProductsMapper;
import model.CategoriesExample;
import model.ProductsExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractTestClass {
    private static SqlSession session;
    private static dao.CategoriesMapper categoriesMapper;
    private static model.CategoriesExample categoriesExample;
    private static dao.ProductsMapper productsMapper;
    private static model.ProductsExample productsExample;

    @BeforeAll
    static void beforeAll() throws IOException
    {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();

        categoriesMapper = session.getMapper(dao.CategoriesMapper.class);
        categoriesExample = new model.CategoriesExample();

        productsMapper = session.getMapper(dao.ProductsMapper.class);
        productsExample = new model.ProductsExample();
    }

    public static SqlSession getSession() {
        return session;
    }

    public static CategoriesMapper getCategoriesMapper() {
        return categoriesMapper;
    }

    public static CategoriesExample getCategoriesExample() {
        return categoriesExample;
    }

    public static ProductsMapper getProductsMapper() {
        return productsMapper;
    }

    public static ProductsExample getProductsExample() {
        return productsExample;
    }

    @AfterAll
    static void afterAll() {
        session.close();
    }
}
