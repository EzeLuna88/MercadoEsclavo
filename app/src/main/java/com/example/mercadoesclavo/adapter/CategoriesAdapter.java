package com.example.mercadoesclavo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.dto.Categories;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter {

    private List<Categories> categoriesList;
    private CategoriesAdapterListener listener;

    public CategoriesAdapter(List<Categories> categoriesList, CategoriesAdapterListener listener) {
        this.categoriesList = categoriesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_categories, parent, false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Categories categoriaDeLaLista = this.categoriesList.get(position);
        CategoriesViewHolder categoriesViewHolder = (CategoriesViewHolder) holder;
        categoriesViewHolder.bindCategories(categoriaDeLaLista);
    }

    @Override
    public int getItemCount() {
        return this.categoriesList.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private Categories categories;
        @BindView(R.id.textViewCategoriesRow)
        TextView textViewCategoriesRow;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer adapterPosition = getAdapterPosition();
                    Categories categories = categoriesList.get(adapterPosition);
                    listener.informarSeleccionCategories(adapterPosition, categories);

                }
            });
        }

        public void bindCategories(Categories categoriesRow) {
            this.categories = categoriesRow;
            this.textViewCategoriesRow.setText(this.categories.getName());
        }
    }

    public interface CategoriesAdapterListener {
        void informarSeleccionCategories(Integer position, Categories categories);
    }
}
