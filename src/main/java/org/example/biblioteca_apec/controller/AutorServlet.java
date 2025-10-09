package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.AutorDAO;
import org.example.biblioteca_apec.model.Autor;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/autores")
public class AutorServlet extends HttpServlet {

    private AutorDAO autorDAO;

    @Override
    public void init() {
        autorDAO = new AutorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarAutor(request, response);
                    break;
                default:
                    listarAutores(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("insertar".equals(action)) {
                insertarAutor(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarAutor(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarAutores(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Autor> lista = autorDAO.listarTodos();
        request.setAttribute("listaAutores", lista);
        request.getRequestDispatcher("/views/autores/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/autores/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Autor autor = autorDAO.obtenerPorId(id);
        request.setAttribute("autor", autor);
        request.getRequestDispatcher("/views/autores/formulario.jsp").forward(request, response);
    }

    private void insertarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nacionalidad = request.getParameter("nacionalidad");
        String biografia = request.getParameter("biografia");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        boolean activo = request.getParameter("activo") != null;

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autor.setNacionalidad(nacionalidad);
        autor.setBiografia(biografia);
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            autor.setFechaNacimiento(Date.valueOf(fechaNacimientoStr));
        }
        autor.setActivo(activo);

        autorDAO.insertar(autor);
        response.sendRedirect(request.getContextPath() + "/autores");
    }

    private void actualizarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nacionalidad = request.getParameter("nacionalidad");
        String biografia = request.getParameter("biografia");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        boolean activo = request.getParameter("activo") != null;

        Autor autor = new Autor();
        autor.setIdAutor(id);
        autor.setNombre(nombre);
        autor.setApellido(apellido);
        autor.setNacionalidad(nacionalidad);
        autor.setBiografia(biografia);
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            autor.setFechaNacimiento(Date.valueOf(fechaNacimientoStr));
        }
        autor.setActivo(activo);

        autorDAO.actualizar(autor);
        response.sendRedirect(request.getContextPath() + "/autores");
    }

    private void eliminarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        autorDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/autores");
    }
}
