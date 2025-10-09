package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Libro;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public List<Libro> listarTodos() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearLibro(rs));
            }
        }
        return lista;
    }

    public Libro obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM libro WHERE id = ?";
        Libro libro = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    libro = mapearLibro(rs);
                }
            }
        }
        return libro;
    }

    public boolean insertar(Libro libro) throws SQLException {
        String sql = "INSERT INTO libro (descripcion, signatura_topografica, isbn, tipo_bibliografia, " +
                     "autor, editora, anio_publicacion, ciencia, idioma, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setearParametros(pstmt, libro);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Libro libro) throws SQLException {
        String sql = "UPDATE libro SET descripcion = ?, signatura_topografica = ?, isbn = ?, " +
                     "tipo_bibliografia = ?, autor = ?, editora = ?, anio_publicacion = ?, " +
                     "ciencia = ?, idioma = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setearParametros(pstmt, libro);
            pstmt.setInt(11, libro.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM libro WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    private Libro mapearLibro(ResultSet rs) throws SQLException {
        Libro libro = new Libro();
        libro.setId(rs.getInt("id"));
        libro.setDescripcion(rs.getString("descripcion"));
        libro.setSignaturaTopografica(rs.getString("signatura_topografica"));
        libro.setIsbn(rs.getString("isbn"));
        libro.setTipoBibliografia(rs.getInt("tipo_bibliografia"));
        libro.setAutor(rs.getInt("autor"));
        libro.setEditora(rs.getInt("editora"));
        libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
        libro.setCiencia(rs.getInt("ciencia"));
        libro.setIdioma(rs.getInt("idioma"));
        libro.setEstado(rs.getBoolean("estado"));
        return libro;
    }

    private void setearParametros(PreparedStatement pstmt, Libro libro) throws SQLException {
        pstmt.setString(1, libro.getDescripcion());
        pstmt.setString(2, libro.getSignaturaTopografica());
        pstmt.setString(3, libro.getIsbn());
        pstmt.setInt(4, libro.getTipoBibliografia());
        pstmt.setInt(5, libro.getAutor());
        pstmt.setInt(6, libro.getEditora());
        pstmt.setInt(7, libro.getAnioPublicacion());
        pstmt.setInt(8, libro.getCiencia());
        pstmt.setInt(9, libro.getIdioma());
        pstmt.setBoolean(10, libro.isEstado());
    }
}
