package org.example.biblioteca_apec.model;

import java.sql.Date;

public class Empleado {
    private int id;
    private String nombre;
    private String cedula;
    private String tandaLabor;
    private double porcientoComision;
    private Date fechaIngreso;
    private boolean estado;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String cedula, String tandaLabor,
                    double porcientoComision, Date fechaIngreso, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.tandaLabor = tandaLabor;
        this.porcientoComision = porcientoComision;
        this.fechaIngreso = fechaIngreso;
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

    public String getTandaLabor() {
        return tandaLabor;
    }

    public void setTandaLabor(String tandaLabor) {
        this.tandaLabor = tandaLabor;
    }

    public double getPorcientoComision() {
        return porcientoComision;
    }

    public void setPorcientoComision(double porcientoComision) {
        this.porcientoComision = porcientoComision;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
