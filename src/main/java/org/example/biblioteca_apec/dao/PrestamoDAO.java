package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Prestamo;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public List<Prestamo> listarTodos() throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearPrestamo(rs));
            }
        }
        return lista;
    }

    public Prestamo obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM prestamo WHERE id = ?";
        Prestamo prestamo = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    prestamo = mapearPrestamo(rs);
                }
            }
        }
        return prestamo;
    }

    public boolean insertar(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamo (empleado_id, libro_id, usuario_id, fecha_prestamo, " +
                     "fecha_devolucion, monto_dia, dias_excedidos, comentario, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setearParametros(pstmt, prestamo);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Prestamo prestamo) throws SQLException {
        String sql = "UPDATE prestamo SET empleado_id = ?, libro_id = ?, usuario_id = ?, " +
                     "fecha_prestamo = ?, fecha_devolucion = ?, monto_dia = ?, dias_excedidos = ?, " +
                     "comentario = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setearParametros(pstmt, prestamo);
            pstmt.setInt(10, prestamo.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM prestamo WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    private Prestamo mapearPrestamo(ResultSet rs) throws SQLException {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(rs.getInt("id"));
        prestamo.setEmpleadoId(rs.getInt("empleado_id"));
        prestamo.setLibroId(rs.getInt("libro_id"));
        prestamo.setUsuarioId(rs.getInt("usuario_id"));
        prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
        prestamo.setFechaDevolucion(rs.getDate("fecha_devolucion"));
        prestamo.setMontoDia(rs.getDouble("monto_dia"));
        prestamo.setDiasExcedidos(rs.getInt("dias_excedidos"));
        prestamo.setComentario(rs.getString("comentario"));
        prestamo.setEstado(rs.getBoolean("estado"));
        return prestamo;
    }

    private void setearParametros(PreparedStatement pstmt, Prestamo prestamo) throws SQLException {
        pstmt.setInt(1, prestamo.getEmpleadoId());
        pstmt.setInt(2, prestamo.getLibroId());
        pstmt.setInt(3, prestamo.getUsuarioId());
        pstmt.setDate(4, prestamo.getFechaPrestamo());
        pstmt.setDate(5, prestamo.getFechaDevolucion());
        pstmt.setDouble(6, prestamo.getMontoDia());
        pstmt.setInt(7, prestamo.getDiasExcedidos());
        pstmt.setString(8, prestamo.getComentario());
        pstmt.setBoolean(9, prestamo.isEstado());
    }
}
