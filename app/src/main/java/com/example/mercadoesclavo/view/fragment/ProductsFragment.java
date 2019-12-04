package com.example.mercadoesclavo.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.ProductoAdapter;
import com.example.mercadoesclavo.controller.MercadoEsclavoController;
import com.example.mercadoesclavo.dto.Categories;
import com.example.mercadoesclavo.dto.Producto;
import com.example.mercadoesclavo.dto.Results;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductoAdapter.ProductoAdapterListener {

    public static final String KEY_CATEGORIES = "categories";

    private Producto producto;
    private List<Results> productoList;
    private notificadorProducto notificadorProducto;
    @BindView(R.id.progressBarFullScreen)
    ProgressBar progressBar;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorProducto = (notificadorProducto) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();


        Categories category = (Categories) bundle.getSerializable(KEY_CATEGORIES);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMainFragment);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final String id = category.getId();
        final MercadoEsclavoController mercadoEsclavoController = new MercadoEsclavoController(getContext());
        getProducts(recyclerView, id, mercadoEsclavoController);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Integer posicionActual = layoutManager.findLastVisibleItemPosition();
                Integer ultimaCelda = layoutManager.getItemCount();
                if (posicionActual.equals(ultimaCelda - 11)) {
                    getProducts(recyclerView, id, mercadoEsclavoController);
                }

            }
        });

        return view;

    }

    private void getProducts(final RecyclerView recyclerView, String id, MercadoEsclavoController mercadoEsclavoController) {
        progressBar.setVisibility(View.VISIBLE);
        if (mercadoEsclavoController.getHayMasProductos()) {
            mercadoEsclavoController.getProductos(new ResultListener<Producto>() {
                @Override
                public void onFinish(Producto result) {
                    producto = result;
                    if (productoList == null) {
                        productoList = result.getResults();
                    } else {
                        productoList.addAll(result.getResults());
                    }
                    ProductoAdapter productoAdapter = new ProductoAdapter(productoList, ProductsFragment.this);
                    recyclerView.setAdapter(productoAdapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }, id);
        }
    }


    @Override
    public void informarSeleccionProducto(Integer posicion, Results results) {
        notificadorProducto.enviarNotificacionProducto(results, posicion);
    }

    public interface notificadorProducto {
        public void enviarNotificacionProducto(Results results, Integer posicion);
    }
}
