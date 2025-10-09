package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.TipoBibliografia;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoBibliografiaDAO {

    public List<TipoBibliografia> listarTodos() throws SQLException {
        List<TipoBibliografia> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_bibliografia ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoBibliografia tipo = new TipoBibliografia();
                tipo.setId(rs.getInt("id"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipo.setEstado(rs.getBoolean("estado"));
                lista.add(tipo);
            }
        }
        return lista;
    }

    public TipoBibliografia obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tipo_bibliografia WHERE id = ?";
        TipoBibliografia tipo = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tipo = new TipoBibliografia();
                    tipo.setId(rs.getInt("id"));
                    tipo.setDescripcion(rs.getString("descripcion"));
                    tipo.setEstado(rs.getBoolean("estado"));
                }
            }
        }
        return tipo;
    }

    public boolean insertar(TipoBibliografia tipo) throws SQLException {
        String sql = "INSERT INTO tipo_bibliografia (descripcion, estado) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo.getDescripcion());
            pstmt.setBoolean(2, tipo.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(TipoBibliografia tipo) throws SQLException {
        String sql = "UPDATE tipo_bibliografia SET descripcion = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo.getDescripcion());
            pstmt.setBoolean(2, tipo.isEstado());
            pstmt.setInt(3, tipo.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM tipo_bibliografia WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
