package com.example.mercadoesclavo.service;

import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MercadoLibreService {

    @GET("categories")
    Call<List<Categories>> getCategories();

    @GET("search?category=")
    Call<Producto> getProductos(
            @Query("category")
                    String id
    );

}
