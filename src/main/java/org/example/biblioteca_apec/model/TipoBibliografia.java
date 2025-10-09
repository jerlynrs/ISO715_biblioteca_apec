package org.example.biblioteca_apec.model;

import java.sql.Timestamp;

public class TipoBibliografia {
    private int idTipoBibliografia;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;

    public TipoBibliografia() {
    }

    public TipoBibliografia(int idTipoBibliografia, String nombre, String descripcion, boolean activo) {
        this.idTipoBibliografia = idTipoBibliografia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdTipoBibliografia() {
        return idTipoBibliografia;
    }

    public void setIdTipoBibliografia(int idTipoBibliografia) {
        this.idTipoBibliografia = idTipoBibliografia;
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
