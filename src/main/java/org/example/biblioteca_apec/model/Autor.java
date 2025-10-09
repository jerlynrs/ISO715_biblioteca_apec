package org.example.biblioteca_apec.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Autor {
    private int idAutor;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String biografia;
    private Date fechaNacimiento;
    private boolean activo;
    private Timestamp fechaCreacion;
    private Timestamp fechaModificacion;

    public Autor() {
    }

    public Autor(int idAutor, String nombre, String apellido, String nacionalidad) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
