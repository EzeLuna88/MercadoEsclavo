package com.example.mercadoesclavo.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.adapter.CategoriesAdapter;
import com.example.mercadoesclavo.controller.CategoriesController;
import com.example.mercadoesclavo.model.Categories;
import com.example.mercadoesclavo.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements CategoriesAdapter.CategoriesAdapterListener {

    private List<Categories> categoriesList = new ArrayList<>();
    private notificadorCategories notificadorCategories;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.notificadorCategories = (notificadorCategories) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCategoriesFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        final CategoriesController categoriesController = new CategoriesController();
        categoriesController.getCategories(new ResultListener<List<Categories>>() {
            @Override
            public void onFinish(List<Categories> result) {
                categoriesList = result;

                CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesList, CategoriesFragment.this);
                recyclerView.setAdapter(categoriesAdapter);


            }
        });

        return view;
    }

    @Override
    public void informarSeleccionCategories(Integer position, Categories categories) {
        notificadorCategories.enviarNotificacionCategories(categories, position);
    }

    public interface notificadorCategories {
        public void enviarNotificacionCategories(Categories categories, Integer position);
    }



}
