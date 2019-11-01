package com.example.mercadoesclavo;

import com.example.mercadoesclavo.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    public List<Producto> getProducto() {
        List<Producto> productoList = new ArrayList<>();
        productoList.add(new Producto("Computadora", "25000", R.drawable.computadora));
        productoList.add(new Producto("Balanza Digital", "1300", R.drawable.balanzadigital));
        productoList.add(new Producto("celular", "15000", R.drawable.celular));
        productoList.add(new Producto("Champagne Dada", "670", R.drawable.champagne));
        productoList.add(new Producto("Cocina Industrial", "35784", R.drawable.cocinaindustrial));
        productoList.add(new Producto("Combo Tostadora y Pava", "2479", R.drawable.combotostadoraypava));
        productoList.add(new Producto("Gazebo", "5000", R.drawable.gazebo));
        productoList.add(new Producto("Impresora", "4000", R.drawable.impresora));
        productoList.add(new Producto("Perchero", "200", R.drawable.perchero));
        productoList.add(new Producto("Reloj", "1300", R.drawable.reloj));
        productoList.add(new Producto("Set mesas y sillas", "6000", R.drawable.setmesasysillas));
        productoList.add(new Producto("Short deportivo", "750", R.drawable.shortdeportivo));
        productoList.add(new Producto("Smart Tv 40 pulgadas", "30000", R.drawable.smarttv));
        productoList.add(new Producto("Sommier", "20000", R.drawable.sommier));
        productoList.add(new Producto("Termo", "1000", R.drawable.termo));
        productoList.add(new Producto("Toalla y toallon", "1250", R.drawable.toallaytoallon));
        productoList.add(new Producto("Zapatillas Kappa", "1999", R.drawable.zapatillas));
        return  productoList;
    }
}
