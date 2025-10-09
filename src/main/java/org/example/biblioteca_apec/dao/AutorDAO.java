package org.example.biblioteca_apec.dao;

import org.example.biblioteca_apec.model.Autor;
import org.example.biblioteca_apec.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public List<Autor> listarTodos() throws SQLException {
        List<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autores ORDER BY apellido, nombre";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor();
                autor.setIdAutor(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setApellido(rs.getString("apellido"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setBiografia(rs.getString("biografia"));
                autor.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                autor.setActivo(rs.getBoolean("activo"));
                autor.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                autor.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
                lista.add(autor);
            }
        }
        return lista;
    }

    public Autor obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM autores WHERE id_autor = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Autor autor = new Autor();
                autor.setIdAutor(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setApellido(rs.getString("apellido"));
                autor.setNacionalidad(rs.getString("nacionalidad"));
                autor.setBiografia(rs.getString("biografia"));
                autor.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                autor.setActivo(rs.getBoolean("activo"));
                autor.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                autor.setFechaModificacion(rs.getTimestamp("fecha_modificacion"));
                return autor;
            }
        }
        return null;
    }

    public boolean insertar(Autor autor) throws SQLException {
        String sql = "INSERT INTO autores (nombre, apellido, nacionalidad, biografia, fecha_nacimiento, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getApellido());
            pstmt.setString(3, autor.getNacionalidad());
            pstmt.setString(4, autor.getBiografia());
            pstmt.setDate(5, autor.getFechaNacimiento());
            pstmt.setBoolean(6, autor.isActivo());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Autor autor) throws SQLException {
        String sql = "UPDATE autores SET nombre = ?, apellido = ?, nacionalidad = ?, biografia = ?, fecha_nacimiento = ?, activo = ?, fecha_modificacion = CURRENT_TIMESTAMP WHERE id_autor = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getApellido());
            pstmt.setString(3, autor.getNacionalidad());
            pstmt.setString(4, autor.getBiografia());
            pstmt.setDate(5, autor.getFechaNacimiento());
            pstmt.setBoolean(6, autor.isActivo());
            pstmt.setInt(7, autor.getIdAutor());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM autores WHERE id_autor = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}
