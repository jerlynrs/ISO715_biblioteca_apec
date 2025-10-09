package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Autor;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public List<Autor> listarTodos() throws SQLException {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autor ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNombre(rs.getString("nombre"));
                autor.setPaisOrigen(rs.getString("pais_origen"));
                autor.setIdiomaNativo(rs.getInt("idioma_nativo"));
                autor.setEstado(rs.getBoolean("estado"));
                lista.add(autor);
            }
        }
        return lista;
    }

    public Autor obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM autor WHERE id = ?";
        Autor autor = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    autor = new Autor();
                    autor.setId(rs.getInt("id"));
                    autor.setNombre(rs.getString("nombre"));
                    autor.setPaisOrigen(rs.getString("pais_origen"));
                    autor.setIdiomaNativo(rs.getInt("idioma_nativo"));
                    autor.setEstado(rs.getBoolean("estado"));
                }
            }
        }
        return autor;
    }

    public boolean insertar(Autor autor) throws SQLException {
        String sql = "INSERT INTO autor (nombre, pais_origen, idioma_nativo, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getPaisOrigen());
            pstmt.setInt(3, autor.getIdiomaNativo());
            pstmt.setBoolean(4, autor.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Autor autor) throws SQLException {
        String sql = "UPDATE autor SET nombre = ?, pais_origen = ?, idioma_nativo = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getPaisOrigen());
            pstmt.setInt(3, autor.getIdiomaNativo());
            pstmt.setBoolean(4, autor.isEstado());
            pstmt.setInt(5, autor.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM autor WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
