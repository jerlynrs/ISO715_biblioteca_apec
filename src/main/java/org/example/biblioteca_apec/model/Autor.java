package org.example.biblioteca_apec.model;

public class Autor {
    private int id;
    private String nombre;
    private String paisOrigen;
    private int idiomaNativo;
    private boolean estado;

    public Autor() {
    }

    public Autor(int id, String nombre, String paisOrigen, int idiomaNativo, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.idiomaNativo = idiomaNativo;
        this.estado = estado;
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

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getIdiomaNativo() {
        return idiomaNativo;
    }

    public void setIdiomaNativo(int idiomaNativo) {
        this.idiomaNativo = idiomaNativo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
