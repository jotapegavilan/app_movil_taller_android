package com.taller_st.app_mobile_taller.modelos;

import java.io.Serializable;
import java.sql.Timestamp;

public class Project implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private String fecha_creacion;
    private String fecha_fin;
    private String repositorio;
    private int usuario_id;
    private int categoria_id;
    private int lenguaje_id;
    private User usuario;
    private Category categoria;
    private Languaje lenguaje;

    public Project() {
    }

    public Project(int id, String nombre, String descripcion, String fecha_creacion, String fecha_fin, String repositorio, int usuario_id, int categoria_id, int lenguaje_id, User usuario, Category categoria, Languaje lenguaje) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.fecha_fin = fecha_fin;
        this.repositorio = repositorio;
        this.usuario_id = usuario_id;
        this.categoria_id = categoria_id;
        this.lenguaje_id = lenguaje_id;
        this.usuario = usuario;
        this.categoria = categoria;
        this.lenguaje = lenguaje;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public int getLenguaje_id() {
        return lenguaje_id;
    }

    public void setLenguaje_id(int lenguaje_id) {
        this.lenguaje_id = lenguaje_id;
    }

    public Languaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Languaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                ", fecha_fin='" + fecha_fin + '\'' +
                ", repositorio='" + repositorio + '\'' +
                ", usuario_id=" + usuario_id +
                ", categoria_id=" + categoria_id +
                ", lenguaje_id=" + lenguaje_id +
                ", usuario=" + usuario +
                ", categoria=" + categoria +
                ", lenguaje=" + lenguaje +
                '}';
    }
}
