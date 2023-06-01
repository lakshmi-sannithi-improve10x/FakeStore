package com.improve10x.fakestore;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.improve10x.fakestore.models.Product;
import com.improve10x.fakestore.network.FakeApi;
import com.improve10x.fakestore.network.FakeApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void  getCategoryName() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<String>> call = service.fetchCategories();
        List<String> categories = call.execute().body();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        System.out.println(new Gson().toJson(categories));
    }

    @Test
    public void  getProducts() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Product>> call = service.getProducts("electronics");
        List<Product> products = call.execute().body();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        System.out.println(new Gson().toJson(products));
    }
}