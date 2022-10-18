package com.taller_st.app_mobile_taller.modelos;

import java.io.Serializable;

public class Languaje implements Serializable {
    private int id;
    private String nombre;
    private int img;

    public Languaje() {
    }

    public Languaje(int id, String nombre, int img) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
