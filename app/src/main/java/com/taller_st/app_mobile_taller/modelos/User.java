package com.taller_st.app_mobile_taller.modelos;

import java.io.Serializable;
import java.sql.Timestamp;

public class User  implements Serializable {
    private int id;
    private String nombres;
    private String apellidos;
    private String email;
    private String rol;
    private Timestamp fecha_creacion;
    private Timestamp fecha_fin;
    private String token;

    public User() {
    }

    public User(int id, String nombres, String apellidos, String email, String rol, Timestamp fecha_creacion, Timestamp fecha_fin, String token) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.fecha_creacion = fecha_creacion;
        this.fecha_fin = fecha_fin;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Timestamp getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Timestamp fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", fecha_fin=" + fecha_fin +
                ", token='" + token + '\'' +
                '}';
    }
}
