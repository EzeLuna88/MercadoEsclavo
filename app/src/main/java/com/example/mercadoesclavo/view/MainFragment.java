package com.example.mercadoesclavo.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercadoesclavo.adapter.ProductoAdapter;
import com.example.mercadoesclavo.dao.ProductoDao;
import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.model.Producto;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMainFragment);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ProductoDao productoDao = new ProductoDao();
        List<Producto> productoList = productoDao.getProducto();
        ProductoAdapter adapter = new ProductoAdapter(productoList);
        recyclerView.setAdapter(adapter);
        return view;

    }

    public interface notificadorMain {
        public void hicieronClickMain();
    }
}
