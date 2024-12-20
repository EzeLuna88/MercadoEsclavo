package com.example.mercadoesclavo.dto;

import java.io.Serializable;

public class UserMercadoEsclavo implements Serializable {

    private String nombre;
    private String apellido;
    private String edad;
    private String telefono;

    public UserMercadoEsclavo(String nombre, String apellido, String edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public UserMercadoEsclavo() {
    }
}
