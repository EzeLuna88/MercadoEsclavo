package com.example.mercadoesclavo.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
public class ProductsFragment extends Fragment {

    public static final String KEY_CATEGORIES = "categories";
    public static final String KEY_POSITION = "position";
    private Producto producto;
    private List<Results> productoList;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        Bundle bundle = getArguments();
        Categories category = (Categories) bundle.getSerializable(KEY_CATEGORIES);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMainFragment);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        String id = category.getId();
        CategoriesController categoriesController = new CategoriesController();
        categoriesController.getProductos(new ResultListener<Producto>() {
            @Override
            public void onFinish(Producto result) {
                producto = result;
                productoList = result.getResults();
                ProductoAdapter productoAdapter = new ProductoAdapter(productoList);
                recyclerView.setAdapter(productoAdapter);


            }
        }, id);







        /*StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<Producto> productoList = productoDao.getProducto();
        ProductoAdapter adapter = new ProductoAdapter(productoList);
        recyclerView.setAdapter(adapter);*/
        return view;

    }

    public interface notificadorMain {
        public void hicieronClickMain();
    }
}
