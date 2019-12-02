package com.example.mercadoesclavo.dao;

import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.model.Description;
import com.example.mercadoesclavo.model.DetalleProducto;
import com.example.mercadoesclavo.model.Producto;
import com.example.mercadoesclavo.service.MercadoLibreService;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MercadoLibreDao {

    public static final String BASE_URL = "https://api.mercadolibre.com/sites/MLA/";


    private Retrofit retrofit;
    private MercadoLibreService mercadoLibreService;



    public MercadoLibreDao() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mercadoLibreService = retrofit.create(MercadoLibreService.class);
    }

    public void getCategories(final ResultListener<List<Categories>> controllerListener) {
        Call<List<Categories>> call = mercadoLibreService.getCategories();

        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                List<Categories> categories = response.body();
                controllerListener.onFinish(categories);
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {

            }
        });
    }

    public void getProductos(final ResultListener<Producto> controllerListener, String id, Integer offset, Integer limit) {
        Call<Producto> call = mercadoLibreService.getProductos(id, offset, limit);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                Producto producto = response.body();
                controllerListener.onFinish(producto);
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getDetalleProducto(final ResultListener<DetalleProducto> controllerListener, String id) {
        Call<DetalleProducto> call = mercadoLibreService.getDetalleProducto(id);
        call.enqueue(new Callback<DetalleProducto>() {
            @Override
            public void onResponse(Call<DetalleProducto> call, Response<DetalleProducto> response) {
                DetalleProducto detalleProducto = response.body();
                controllerListener.onFinish(detalleProducto);
            }

            @Override
            public void onFailure(Call<DetalleProducto> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getDescription(final ResultListener<Description> controllerListener, String id) {
        Call<Description> call = mercadoLibreService.getDescription(id);
        call.enqueue(new Callback<Description>() {
            @Override
            public void onResponse(Call<Description> call, Response<Description> response) {
                Description description = response.body();
                controllerListener.onFinish(description);
            }

            @Override
            public void onFailure(Call<Description> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
