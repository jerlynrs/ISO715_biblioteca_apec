package org.example.biblioteca_apec.model;

public class Editora {
    private int id;
    private String descripcion;
    private boolean estado;

    public Editora() {
    }

    public Editora(int id, String descripcion, boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
