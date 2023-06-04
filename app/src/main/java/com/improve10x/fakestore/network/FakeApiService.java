package com.improve10x.fakestore.network;

import com.improve10x.fakestore.models.Cart;
import com.improve10x.fakestore.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeApiService {

    @GET("api/v1/categories")
    Call<List<Product>> fetchCategories();

    @GET("products/category/{categoryName}")
    Call<List<Product>>  getProducts(@Path("categoryName") String categoryName);

    @GET("products/{productId}")
    Call<Product> fetchProductDetails(@Path("productId") int productId);

    @GET("carts/{cartId}")
    Call<Cart> getCartItems(@Path("cartId") int cartId);
}
