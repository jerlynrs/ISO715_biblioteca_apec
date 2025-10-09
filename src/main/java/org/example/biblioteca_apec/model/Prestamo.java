package org.example.biblioteca_apec.model;

import java.sql.Date;

public class Prestamo {
    private int id;
    private int empleadoId;
    private int libroId;
    private int usuarioId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private double montoDia;
    private int diasExcedidos;
    private String comentario;
    private boolean estado;

    public Prestamo() {
    }

    public Prestamo(int id, int empleadoId, int libroId, int usuarioId,
                    Date fechaPrestamo, Date fechaDevolucion, double montoDia,
                    int diasExcedidos, String comentario, boolean estado) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.montoDia = montoDia;
        this.diasExcedidos = diasExcedidos;
        this.comentario = comentario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getMontoDia() {
        return montoDia;
    }

    public void setMontoDia(double montoDia) {
        this.montoDia = montoDia;
    }

    public int getDiasExcedidos() {
        return diasExcedidos;
    }

    public void setDiasExcedidos(int diasExcedidos) {
        this.diasExcedidos = diasExcedidos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
