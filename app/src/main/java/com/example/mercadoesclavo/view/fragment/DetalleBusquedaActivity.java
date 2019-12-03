package com.example.mercadoesclavo.view.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.ViewPagerImagenProductoAdapter;
import com.example.mercadoesclavo.model.Description;
import com.example.mercadoesclavo.model.Producto;
import com.example.mercadoesclavo.model.Results;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class DetalleBusquedaActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private Description description;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager pager;
    private ViewPagerImagenProductoAdapter pagerAdapter;

    public static final String KEY_PRODUCTO = "producto";
    public static final String KEY_POSICION = "posicion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_busqueda);

        pager = findViewById(R.id.viewPagerBusqueda);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer posicion = bundle.getInt(KEY_POSICION);

        viewPager(bundle, posicion);

    }

    private void viewPager(Bundle bundle, Integer posicion) {
        Producto producto = (Producto) bundle.getSerializable(KEY_PRODUCTO);
        List<Results> resultsList = producto.getResults();
        cargarListaDeProductos(resultsList);

        pager.setCurrentItem(posicion);
    }

    public void cargarListaDeProductos(List<Results> resultsList) {
        for (Results results : resultsList) {
            DetalleBusquedaFragment detalleBusquedaFragment = DetalleBusquedaFragment.getInstance(results);
            fragmentList.add(detalleBusquedaFragment);
        }
        pagerAdapter = new ViewPagerImagenProductoAdapter(getSupportFragmentManager(), fragmentList);
        pager.setAdapter(pagerAdapter);
    }
}
