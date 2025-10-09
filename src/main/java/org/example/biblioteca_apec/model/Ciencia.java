package org.example.biblioteca_apec.model;

import java.sql.Timestamp;

public class Ciencia {
    private int idCiencia;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;

    public Ciencia() {
    }

    public Ciencia(int idCiencia, String nombre, String descripcion, boolean activo) {
        this.idCiencia = idCiencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdCiencia() {
        return idCiencia;
    }

    public void setIdCiencia(int idCiencia) {
        this.idCiencia = idCiencia;
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
