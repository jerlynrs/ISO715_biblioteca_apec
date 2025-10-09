package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Usuario;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }
        return lista;
    }

    public Usuario obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }
        }
        return usuario;
    }

    public boolean insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, cedula, no_carnet, tipo_persona, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCedula());
            pstmt.setString(3, usuario.getNoCarnet());
            pstmt.setString(4, usuario.getTipoPersona());
            pstmt.setBoolean(5, usuario.isEstado());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nombre = ?, cedula = ?, no_carnet = ?, tipo_persona = ?, estado = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCedula());
            pstmt.setString(3, usuario.getNoCarnet());
            pstmt.setString(4, usuario.getTipoPersona());
            pstmt.setBoolean(5, usuario.isEstado());
            pstmt.setInt(6, usuario.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setCedula(rs.getString("cedula"));
        usuario.setNoCarnet(rs.getString("no_carnet"));
        usuario.setTipoPersona(rs.getString("tipo_persona"));
        usuario.setEstado(rs.getBoolean("estado"));
        return usuario;
    }
}
