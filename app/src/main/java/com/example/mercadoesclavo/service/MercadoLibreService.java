package com.example.mercadoesclavo.service;

import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.model.Description;
import com.example.mercadoesclavo.model.DetalleProducto;
import com.example.mercadoesclavo.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercadoLibreService {

    @GET("/sites/MLA/categories")
    Call<List<Categories>> getCategories();

    @GET("/sites/MLA/search?category=")
    Call<Producto> getProductos(
            @Query("category") String id,
            @Query("offset") Integer offset,
            @Query("limit") Integer limit
    );

    @GET("/items/{id}")
    Call<DetalleProducto> getDetalleProducto(
            @Path("id")
                    String id);

    @GET("/items/{id}/description")
    Call<Description> getDescription(
            @Path("id")
                    String id);

    @GET("/sites/MLA/search?")
    Call<Producto> getProductosBusqueda(
            @Query("q") String key);




}
