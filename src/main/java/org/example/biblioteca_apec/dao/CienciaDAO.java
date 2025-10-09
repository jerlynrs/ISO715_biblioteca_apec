package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Ciencia;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CienciaDAO {

    public List<Ciencia> listarTodos() throws SQLException {
        List<Ciencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM ciencia ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ciencia ciencia = new Ciencia();
                ciencia.setId(rs.getInt("id"));
                ciencia.setDescripcion(rs.getString("descripcion"));
                ciencia.setEstado(rs.getBoolean("estado"));
                lista.add(ciencia);
            }
        }
        return lista;
    }

    public Ciencia obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ciencia WHERE id = ?";
        Ciencia ciencia = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ciencia = new Ciencia();
                    ciencia.setId(rs.getInt("id"));
                    ciencia.setDescripcion(rs.getString("descripcion"));
                    ciencia.setEstado(rs.getBoolean("estado"));
                }
            }
        }
        return ciencia;
    }

    public boolean insertar(Ciencia ciencia) throws SQLException {
        String sql = "INSERT INTO ciencia (descripcion, estado) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ciencia.getDescripcion());
            pstmt.setBoolean(2, ciencia.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Ciencia ciencia) throws SQLException {
        String sql = "UPDATE ciencia SET descripcion = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ciencia.getDescripcion());
            pstmt.setBoolean(2, ciencia.isEstado());
            pstmt.setInt(3, ciencia.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM ciencia WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
