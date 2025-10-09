package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Idioma;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdiomaDAO {

    public List<Idioma> listarTodos() throws SQLException {
        List<Idioma> lista = new ArrayList<>();
        String sql = "SELECT * FROM idioma ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Idioma idioma = new Idioma();
                idioma.setId(rs.getInt("id"));
                idioma.setDescripcion(rs.getString("descripcion"));
                idioma.setEstado(rs.getBoolean("estado"));
                lista.add(idioma);
            }
        }
        return lista;
    }

    public Idioma obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM idioma WHERE id = ?";
        Idioma idioma = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    idioma = new Idioma();
                    idioma.setId(rs.getInt("id"));
                    idioma.setDescripcion(rs.getString("descripcion"));
                    idioma.setEstado(rs.getBoolean("estado"));
                }
            }
        }
        return idioma;
    }

    public boolean insertar(Idioma idioma) throws SQLException {
        String sql = "INSERT INTO idioma (descripcion, estado) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idioma.getDescripcion());
            pstmt.setBoolean(2, idioma.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Idioma idioma) throws SQLException {
        String sql = "UPDATE idioma SET descripcion = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idioma.getDescripcion());
            pstmt.setBoolean(2, idioma.isEstado());
            pstmt.setInt(3, idioma.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM idioma WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
