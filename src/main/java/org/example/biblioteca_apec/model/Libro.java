package org.example.biblioteca_apec.model;

public class Libro {
    private int id;
    private String descripcion;
    private String signaturaTopografica;
    private String isbn;
    private int tipoBibliografia;
    private int autor;
    private int editora;
    private int anioPublicacion;
    private int ciencia;
    private int idioma;
    private boolean estado;

    public Libro() {
    }

    public Libro(int id, String descripcion, String signaturaTopografica, String isbn,
                 int tipoBibliografia, int autor, int editora, int anioPublicacion,
                 int ciencia, int idioma, boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.signaturaTopografica = signaturaTopografica;
        this.isbn = isbn;
        this.tipoBibliografia = tipoBibliografia;
        this.autor = autor;
        this.editora = editora;
        this.anioPublicacion = anioPublicacion;
        this.ciencia = ciencia;
        this.idioma = idioma;
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

    public String getSignaturaTopografica() {
        return signaturaTopografica;
    }

    public void setSignaturaTopografica(String signaturaTopografica) {
        this.signaturaTopografica = signaturaTopografica;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTipoBibliografia() {
        return tipoBibliografia;
    }

    public void setTipoBibliografia(int tipoBibliografia) {
        this.tipoBibliografia = tipoBibliografia;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getEditora() {
        return editora;
    }

    public void setEditora(int editora) {
        this.editora = editora;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public int getCiencia() {
        return ciencia;
    }

    public void setCiencia(int ciencia) {
        this.ciencia = ciencia;
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
