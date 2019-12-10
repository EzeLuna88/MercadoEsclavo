package com.example.mercadoesclavo.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mercadoesclavo.config.AppDatabase;
import com.example.mercadoesclavo.dao.MercadoLibreDao;
//import com.example.mercadoesclavo.dao.RoomCategoriesDao;
import com.example.mercadoesclavo.dao.RoomCategoriesDao;
import com.example.mercadoesclavo.dao.RoomCityDao;
import com.example.mercadoesclavo.dao.RoomDescriptionDao;
import com.example.mercadoesclavo.dao.RoomDetalleProductoDao;
import com.example.mercadoesclavo.dao.RoomResultsDao;
import com.example.mercadoesclavo.dao.RoomSellerAddressDao;
import com.example.mercadoesclavo.dto.Categories;
import com.example.mercadoesclavo.dto.Description;
import com.example.mercadoesclavo.dto.DetalleProducto;
import com.example.mercadoesclavo.dto.Producto;
import com.example.mercadoesclavo.dto.Results;
import com.example.mercadoesclavo.utils.ResultListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MercadoEsclavoController {

    private MercadoLibreDao mercadoLibreDao;
    private RoomCategoriesDao roomCategoriesDao;
    private RoomResultsDao roomResultsDao;
    private RoomDescriptionDao roomDescriptionDao;
    private RoomDetalleProductoDao roomDetalleProductoDao;
    private RoomSellerAddressDao roomSellerAddressDao;
    private RoomCityDao roomCityDao;
    private Context context;
    private Integer offset = 0;
    private Integer limit = 50;
    private Boolean hayMasProductos = true;


    public MercadoEsclavoController(Context context) {
        this.context = context;
        this.mercadoLibreDao = new MercadoLibreDao();
        this.roomCategoriesDao = AppDatabase.getInstance(context).roomCategoriesDao();
        this.roomResultsDao = AppDatabase.getInstance(context).roomResultsDao();
        this.roomDetalleProductoDao = AppDatabase.getInstance(context).roomDetalleProductoDao();
        this.roomDescriptionDao = AppDatabase.getInstance(context).roomDescriptionDao();
        this.roomSellerAddressDao = AppDatabase.getInstance(context).roomSellerAddressDao();
        this.roomCityDao = AppDatabase.getInstance(context).roomCityDao();
    }

    public void getCategories(final ResultListener<List<Categories>> viewController) {
        if (hayInternet()) {
            mercadoLibreDao.getCategories(new ResultListener<List<Categories>>() {
                @Override
                public void onFinish(List<Categories> result) {
                    roomCategoriesDao.delete();
                    roomCategoriesDao.insert(result);
                    viewController.onFinish(result);
                }
            });
        } else {
            List<Categories> categoriesList = roomCategoriesDao.getAll();
            viewController.onFinish(categoriesList);
        }
    }

    public void getProductos(final ResultListener<Producto> viewController, String id) {
        if (hayInternet()) {
            mercadoLibreDao.getProductos(new ResultListener<Producto>() {
                @Override
                public void onFinish(Producto result) {
                    if (result.getResults().size() < limit) {
                        hayMasProductos = false;
                    }
                    offset = offset + limit;
                    roomResultsDao.delete();

                    roomSellerAddressDao.insert();
                    roomResultsDao.insert(result.getResults());
                    viewController.onFinish(result);
                }
            }, id, offset, limit);
        } else {
            List<Results> resultsList = roomResultsDao.getAll();
            Producto producto = new Producto(resultsList);
            viewController.onFinish(producto);
        }
    }

    public void getFavoritos(final ResultListener<List<DetalleProducto>> viewListener, FirebaseFirestore db) {
        if (hayInternet()) {
            mercadoLibreDao.getFavoritos(new ResultListener<List<DetalleProducto>>() {
                @Override
                public void onFinish(List<DetalleProducto> result) {
                    roomDetalleProductoDao.delete();
                    roomDetalleProductoDao.insert(result);
                    viewListener.onFinish(result);
                }
            }, db);
        } else {
            List<DetalleProducto> detalleProductoList = roomDetalleProductoDao.getDetalleProductoList();
            viewListener.onFinish(detalleProductoList);
        }
    }


    public Boolean getHayMasProductos() {
        return hayMasProductos;
    }

    public void getDetalleProducto(final ResultListener<DetalleProducto> viewController, String id) {
        if (hayInternet()) {
            mercadoLibreDao.getDetalleProducto(new ResultListener<DetalleProducto>() {
                @Override
                public void onFinish(DetalleProducto result) {
                    roomDetalleProductoDao.delete();
                    roomDetalleProductoDao.insert(result);
                    viewController.onFinish(result);
                }
            }, id);
        } else {
            DetalleProducto detalleProducto = roomDetalleProductoDao.getAll();
            viewController.onFinish(detalleProducto);
        }
    }

    public void getDescription(final ResultListener<Description> viewController, String id) {
        if (hayInternet()) {
            mercadoLibreDao.getDescription(new ResultListener<Description>() {
                @Override
                public void onFinish(Description result) {
                    roomDescriptionDao.delete();
                    roomDescriptionDao.insert(result);
                    viewController.onFinish(result);
                }
            }, id);
        } else {
            Description description = roomDescriptionDao.getAll();
            viewController.onFinish(description);
        }
    }

    public void getProductosBusqueda(final ResultListener<Producto> viewController, String id) {
        if (hayInternet()) {
            mercadoLibreDao.getProductosBusqueda(new ResultListener<Producto>() {
                @Override
                public void onFinish(Producto result) {
                    roomResultsDao.delete();
                    roomResultsDao.insert(result.getResults());
                    viewController.onFinish(result);
                }
            }, id);
        } else {
            List<Results> resultsList = roomResultsDao.getAll();
            Producto producto = new Producto(resultsList);
            viewController.onFinish(producto);
        }
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
