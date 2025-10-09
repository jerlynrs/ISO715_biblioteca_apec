package org.example.biblioteca_apec.model;

public class Usuario {
    private int id;
    private String nombre;
    private String cedula;
    private String noCarnet;
    private String tipoPersona;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String cedula, String noCarnet, String tipoPersona, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.noCarnet = noCarnet;
        this.tipoPersona = tipoPersona;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNoCarnet() {
        return noCarnet;
    }

    public void setNoCarnet(String noCarnet) {
        this.noCarnet = noCarnet;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
