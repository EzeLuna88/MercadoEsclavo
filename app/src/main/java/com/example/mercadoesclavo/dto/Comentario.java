package com.example.mercadoesclavo.dto;

public class Comentario {

    private String email;
    private String comentario;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Comentario(String email, String comentario) {
        this.email = email;
        this.comentario = comentario;
    }

    public Comentario() {
    }
}
