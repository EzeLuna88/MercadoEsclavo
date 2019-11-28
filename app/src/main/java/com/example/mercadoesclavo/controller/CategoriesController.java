package com.example.mercadoesclavo.controller;

import com.example.mercadoesclavo.dao.MercadoLibreDao;
import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.model.Producto;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.List;

import javax.xml.transform.Result;

public class CategoriesController {

    private MercadoLibreDao mercadoLibreDao;

    public CategoriesController() {
        this.mercadoLibreDao = new MercadoLibreDao();
    }

    public void getCategories(final ResultListener<List<Categories>> viewController) {
        mercadoLibreDao.getCategories(new ResultListener<List<Categories>>() {
            @Override
            public void onFinish(List<Categories> result) {
                viewController.onFinish(result);
            }
        });
    }

    /*public void getProductos(final ResultListener<List<Producto>> viewController, String id){
        mercadoLibreDao.getProductos(new ResultListener<List<Producto>>() {
            @Override
            public void onFinish(List<Producto> result) {
                viewController.onFinish(result);
            }
        }, id);
    }
*/

    public void getProductos(final ResultListener<Producto> viewController, String id) {
        mercadoLibreDao.getProductos(new ResultListener<Producto>() {
            @Override
            public void onFinish(Producto result) {
                viewController.onFinish(result);
            }
        }, id);
    }
}
