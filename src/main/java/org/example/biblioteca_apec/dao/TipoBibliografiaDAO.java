package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.TipoBibliografia;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoBibliografiaDAO {

    public List<TipoBibliografia> listarTodos() throws SQLException {
        List<TipoBibliografia> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipos_bibliografia ORDER BY id_tipo_bibliografia";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoBibliografia tipo = new TipoBibliografia();
                tipo.setIdTipoBibliografia(rs.getInt("id_tipo_bibliografia"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipo.setActivo(rs.getBoolean("activo"));
                tipo.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tipo.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
                lista.add(tipo);
            }
        }
        return lista;
    }

    public TipoBibliografia obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tipos_bibliografia WHERE id_tipo_bibliografia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                TipoBibliografia tipo = new TipoBibliografia();
                tipo.setIdTipoBibliografia(rs.getInt("id_tipo_bibliografia"));
                tipo.setNombre(rs.getString("nombre"));
                tipo.setDescripcion(rs.getString("descripcion"));
                tipo.setActivo(rs.getBoolean("activo"));
                tipo.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                tipo.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
                return tipo;
            }
        }
        return null;
    }

    public boolean insertar(TipoBibliografia tipo) throws SQLException {
        String sql = "INSERT INTO tipos_bibliografia (nombre, descripcion, activo) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo.getNombre());
            pstmt.setString(2, tipo.getDescripcion());
            pstmt.setBoolean(3, tipo.isActivo());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(TipoBibliografia tipo) throws SQLException {
        String sql = "UPDATE tipos_bibliografia SET nombre = ?, descripcion = ?, activo = ?, fecha_modificacion = CURRENT_TIMESTAMP WHERE id_tipo_bibliografia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo.getNombre());
            pstmt.setString(2, tipo.getDescripcion());
            pstmt.setBoolean(3, tipo.isActivo());
            pstmt.setInt(4, tipo.getIdTipoBibliografia());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM tipos_bibliografia WHERE id_tipo_bibliografia = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
