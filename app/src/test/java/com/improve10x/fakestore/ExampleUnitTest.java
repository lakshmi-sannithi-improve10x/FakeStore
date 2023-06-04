package com.improve10x.fakestore;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.improve10x.fakestore.models.Cart;
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
    public void getCategoryName() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Product>> call = service.fetchCategories();
        List<Product>  products= call.execute().body();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        System.out.println(new Gson().toJson(products));
    }

    @Test
    public void getProducts() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Product>> call = service.getProducts(1);
        List<Product> products = call.execute().body();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        System.out.println(new Gson().toJson(products));
    }

    @Test
    public void getProductData() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<Product> call = service.fetchProductDetails(1);
        Product product = call.execute().body();
        assertNotNull(product);
        System.out.println(new Gson().toJson(product));
    }

    @Test
    public void getProductQuantity() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<Cart> call = service.getCartItems(1);
        Cart cart = call.execute().body();
        assertNotNull(cart);
        System.out.println(new Gson().toJson(cart));
    }
}