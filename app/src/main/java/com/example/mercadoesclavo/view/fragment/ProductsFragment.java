package com.example.mercadoesclavo.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.ProductoAdapter;
import com.example.mercadoesclavo.controller.CategoriesController;
import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.model.Producto;
import com.example.mercadoesclavo.model.Results;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements ProductoAdapter.ProductoAdapterListener {

    public static final String KEY_CATEGORIES = "categories";
    public static final String KEY_POSITION = "position";
    private Producto producto;
    private List<Results> productoList;
    private notificadorProducto notificadorProducto;

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

        Bundle bundle = getArguments();
        Categories category = (Categories) bundle.getSerializable(KEY_CATEGORIES);

        String title = category.getName();

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMainFragment);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final String id = category.getId();
        final CategoriesController categoriesController = new CategoriesController();
        getProducts(recyclerView, id, categoriesController);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Integer posicionActual = layoutManager.findFirstVisibleItemPosition();
                Integer ultimaCelda = layoutManager.getItemCount();
                if (posicionActual.equals(ultimaCelda - 10)) {
                    getProducts(recyclerView, id, categoriesController);
                }

            }
        });

        return view;

    }

    private void getProducts(final RecyclerView recyclerView, String id, CategoriesController categoriesController) {
        if (categoriesController.getHayMasProductos()) {
            categoriesController.getProductos(new ResultListener<Producto>() {
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
