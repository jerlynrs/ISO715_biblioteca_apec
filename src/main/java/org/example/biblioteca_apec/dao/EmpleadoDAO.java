package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Empleado;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearEmpleado(rs));
            }
        }
        return lista;
    }

    public Empleado obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM empleado WHERE id = ?";
        Empleado empleado = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    empleado = mapearEmpleado(rs);
                }
            }
        }
        return empleado;
    }

    public boolean insertar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleado (nombre, cedula, tanda_labor, porciento_comision, fecha_ingreso, estado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getCedula());
            pstmt.setString(3, empleado.getTandaLabor());
            pstmt.setDouble(4, empleado.getPorcientoComision());
            pstmt.setDate(5, empleado.getFechaIngreso());
            pstmt.setBoolean(6, empleado.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleado SET nombre = ?, cedula = ?, tanda_labor = ?, porciento_comision = ?, " +
                     "fecha_ingreso = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getCedula());
            pstmt.setString(3, empleado.getTandaLabor());
            pstmt.setDouble(4, empleado.getPorcientoComision());
            pstmt.setDate(5, empleado.getFechaIngreso());
            pstmt.setBoolean(6, empleado.isEstado());
            pstmt.setInt(7, empleado.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM empleado WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setId(rs.getInt("id"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setCedula(rs.getString("cedula"));
        empleado.setTandaLabor(rs.getString("tanda_labor"));
        empleado.setPorcientoComision(rs.getDouble("porciento_comision"));
        empleado.setFechaIngreso(rs.getDate("fecha_ingreso"));
        empleado.setEstado(rs.getBoolean("estado"));
        return empleado;
    }
}
