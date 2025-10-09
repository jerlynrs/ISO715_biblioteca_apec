package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Ciencia;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CienciaDAO {

    public List<Ciencia> listarTodos() throws SQLException {
        List<Ciencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM ciencias ORDER BY id_ciencia";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ciencia ciencia = new Ciencia();
                ciencia.setIdCiencia(rs.getInt("id_ciencia"));
                ciencia.setNombre(rs.getString("nombre"));
                ciencia.setDescripcion(rs.getString("descripcion"));
                ciencia.setActivo(rs.getBoolean("activo"));
                ciencia.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                ciencia.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
                lista.add(ciencia);
            }
        }
        return lista;
    }

    public Ciencia obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ciencias WHERE id_ciencia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Ciencia ciencia = new Ciencia();
                ciencia.setIdCiencia(rs.getInt("id_ciencia"));
                ciencia.setNombre(rs.getString("nombre"));
                ciencia.setDescripcion(rs.getString("descripcion"));
                ciencia.setActivo(rs.getBoolean("activo"));
                ciencia.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                ciencia.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
                return ciencia;
            }
        }
        return null;
    }

    public boolean insertar(Ciencia ciencia) throws SQLException {
        String sql = "INSERT INTO ciencias (nombre, descripcion, activo) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ciencia.getNombre());
            pstmt.setString(2, ciencia.getDescripcion());
            pstmt.setBoolean(3, ciencia.isActivo());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Ciencia ciencia) throws SQLException {
        String sql = "UPDATE ciencias SET nombre = ?, descripcion = ?, activo = ?, fecha_modificacion = CURRENT_TIMESTAMP WHERE id_ciencia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ciencia.getNombre());
            pstmt.setString(2, ciencia.getDescripcion());
            pstmt.setBoolean(3, ciencia.isActivo());
            pstmt.setInt(4, ciencia.getIdCiencia());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM ciencias WHERE id_ciencia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
