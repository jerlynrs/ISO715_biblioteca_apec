package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Editora;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

    public List<Editora> listarTodos() throws SQLException {
        List<Editora> lista = new ArrayList<>();
        String sql = "SELECT * FROM editora ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Editora editora = new Editora();
                editora.setId(rs.getInt("id"));
                editora.setDescripcion(rs.getString("descripcion"));
                editora.setEstado(rs.getBoolean("estado"));
                lista.add(editora);
            }
        }
        return lista;
    }

    public Editora obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM editora WHERE id = ?";
        Editora editora = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    editora = new Editora();
                    editora.setId(rs.getInt("id"));
                    editora.setDescripcion(rs.getString("descripcion"));
                    editora.setEstado(rs.getBoolean("estado"));
                }
            }
        }
        return editora;
    }

    public boolean insertar(Editora editora) throws SQLException {
        String sql = "INSERT INTO editora (descripcion, estado) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, editora.getDescripcion());
            pstmt.setBoolean(2, editora.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Editora editora) throws SQLException {
        String sql = "UPDATE editora SET descripcion = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, editora.getDescripcion());
            pstmt.setBoolean(2, editora.isEstado());
            pstmt.setInt(3, editora.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM editora WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
