package com.example.mercadoesclavo.adapter;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mercadoesclavo.R;
import com.example.mercadoesclavo.dto.Comentario;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComentariosAdapter extends RecyclerView.Adapter {

    private List<Comentario> comentarioList;

    public ComentariosAdapter(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_comentarios, parent, false);
        ComentariosViewHolder comentariosViewHolder = new ComentariosViewHolder(view);
        return comentariosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comentario comentarioDeLaLista = this.comentarioList.get(position);
        ComentariosViewHolder comentariosViewHolder = (ComentariosViewHolder) holder;
        comentariosViewHolder.bindComentarios(comentarioDeLaLista);
    }

    @Override
    public int getItemCount() {
        return comentarioList.size();
    }

    class ComentariosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewRowComentariosEmail)
        TextView textViewRowComentariosEmail;
        @BindView(R.id.textViewRowComentariosComentario)
        TextView textViewRowComentariosComentario;
        private Comentario comentario;

        public ComentariosViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindComentarios(Comentario comentarioDeLaCelda) {
            this.comentario = comentarioDeLaCelda;
            SpannableString content = new SpannableString(comentario.getEmail());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            textViewRowComentariosEmail.setText(content);
            textViewRowComentariosComentario.setText(comentario.getComentario());
        }


    }
}
