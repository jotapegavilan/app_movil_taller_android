package com.taller_st.app_mobile_taller.modelos;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String nombre;
    private String color;

    public Category() {
    }

    public Category(int id, String nombre, String color) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String corlor) {
        this.color = corlor;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
