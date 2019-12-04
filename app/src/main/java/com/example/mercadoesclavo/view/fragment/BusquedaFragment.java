package com.example.mercadoesclavo.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.BusquedaAdapter;
import com.example.mercadoesclavo.dto.Producto;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusquedaFragment extends Fragment implements BusquedaAdapter.BusquedaAdapterListener {

    public static final String KEY_PRODUCTOS = "productos";

    @BindView(R.id.progressBarFullScreen)
    ProgressBar progressBar;
    private Producto producto;
        private notificadorBusqueda notificadorBusqueda;

    public BusquedaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorBusqueda = (notificadorBusqueda) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_busqueda, container, false);

        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);
        Bundle bundle = getArguments();
        producto = (Producto) bundle.getSerializable(KEY_PRODUCTOS);

        RecyclerView(view);


        return view;
    }

    private void RecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBusquedaFragment);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        BusquedaAdapter busquedaAdapter = new BusquedaAdapter(producto.getResults(), BusquedaFragment.this);
        recyclerView.setAdapter(busquedaAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void informarSeleccionBusqueda(Integer posicion) {
    notificadorBusqueda.enviarNotificacionBusqueda(producto, posicion);
    }



    public interface notificadorBusqueda {
        public void enviarNotificacionBusqueda(Producto producto, Integer posicion);
    }


}
