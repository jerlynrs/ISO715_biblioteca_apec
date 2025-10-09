package org.example.biblioteca_apec.model;

import java.sql.Timestamp;

public class Editora {
    private int idEditora;
    private String nombre;
    private String pais;
    private String sitioWeb;
    private boolean activo;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;

    public Editora() {
    }

    public Editora(int idEditora, String nombre, String pais, String sitioWeb, boolean activo) {
        this.idEditora = idEditora;
        this.nombre = nombre;
        this.pais = pais;
        this.sitioWeb = sitioWeb;
        this.activo = activo;
    }

    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
