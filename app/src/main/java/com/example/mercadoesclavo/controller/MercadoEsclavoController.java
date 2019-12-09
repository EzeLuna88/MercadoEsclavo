package com.example.mercadoesclavo.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mercadoesclavo.config.AppDatabase;
import com.example.mercadoesclavo.dao.MercadoLibreDao;
//import com.example.mercadoesclavo.dao.RoomMercadoLibreDao;
import com.example.mercadoesclavo.dao.RoomMercadoLibreDao;
import com.example.mercadoesclavo.dto.Categories;
import com.example.mercadoesclavo.dto.Description;
import com.example.mercadoesclavo.dto.DetalleProducto;
import com.example.mercadoesclavo.dto.Producto;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.List;

public class MercadoEsclavoController {

    private MercadoLibreDao mercadoLibreDao;
    private RoomMercadoLibreDao roomMercadoLibreDao;
    private Context context;
    private Integer offset = 0;
    private Integer limit = 50;
    private Boolean hayMasProductos = true;


    public MercadoEsclavoController(Context context) {
        this.context = context;
        this.mercadoLibreDao = new MercadoLibreDao();
        this.roomMercadoLibreDao = AppDatabase.getInstance(context).roomMercadoLibreDao();

    }


    public void getCategories(final ResultListener<List<Categories>> viewController) {
        if (hayInternet()) {
            mercadoLibreDao.getCategories(new ResultListener<List<Categories>>() {
                @Override
                public void onFinish(List<Categories> result) {
                    roomMercadoLibreDao.delete();
                    roomMercadoLibreDao.insert(result);
                    viewController.onFinish(result);
                }
            });
        } else {
            List<Categories> categoriesList = roomMercadoLibreDao.getAll();
            viewController.onFinish(categoriesList);
        }
    }


    public void getProductos(final ResultListener<Producto> viewController, String id) {
        mercadoLibreDao.getProductos(new ResultListener<Producto>() {
            @Override
            public void onFinish(Producto result) {
                if (result.getResults().size() < limit) {
                    hayMasProductos = false;
                }
                offset = offset + limit;
                viewController.onFinish(result);
            }
        }, id, offset, limit);
    }

    public Boolean getHayMasProductos() {
        return hayMasProductos;
    }

    public void getDetalleProducto(
            final ResultListener<DetalleProducto> viewController, String id) {
        mercadoLibreDao.getDetalleProducto(new ResultListener<DetalleProducto>() {
            @Override
            public void onFinish(DetalleProducto result) {
                viewController.onFinish(result);
            }
        }, id);
    }

    public void getDescription(final ResultListener<Description> viewController, String id) {
        mercadoLibreDao.getDescription(new ResultListener<Description>() {
            @Override
            public void onFinish(Description result) {
                viewController.onFinish(result);
            }
        }, id);
    }

    public void getProductosBusqueda(final ResultListener<Producto> viewController, String id) {
        mercadoLibreDao.getProductosBusqueda(new ResultListener<Producto>() {
            @Override
            public void onFinish(Producto result) {
                viewController.onFinish(result);
            }
        }, id);
    }


    public Boolean hayInternet() {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
