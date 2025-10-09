package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.CienciaDAO;
import org.example.biblioteca_apec.model.Ciencia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ciencias")
public class CienciaServlet extends HttpServlet {

    private CienciaDAO cienciaDAO;

    @Override
    public void init() {
        cienciaDAO = new CienciaDAO();
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
                    eliminarCiencia(request, response);
                    break;
                default:
                    listarCiencias(request, response);
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
                insertarCiencia(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarCiencia(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarCiencias(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Ciencia> lista = cienciaDAO.listarTodos();
        request.setAttribute("listaCiencias", lista);
        request.getRequestDispatcher("/views/ciencias/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/ciencias/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ciencia ciencia = cienciaDAO.obtenerPorId(id);
        request.setAttribute("ciencia", ciencia);
        request.getRequestDispatcher("/views/ciencias/formulario.jsp").forward(request, response);
    }

    private void insertarCiencia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        boolean activo = request.getParameter("activo") != null;

        Ciencia ciencia = new Ciencia();
        ciencia.setNombre(nombre);
        ciencia.setDescripcion(descripcion);
        ciencia.setActivo(activo);

        cienciaDAO.insertar(ciencia);
        response.sendRedirect(request.getContextPath() + "/ciencias");
    }

    private void actualizarCiencia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        boolean activo = request.getParameter("activo") != null;

        Ciencia ciencia = new Ciencia();
        ciencia.setIdCiencia(id);
        ciencia.setNombre(nombre);
        ciencia.setDescripcion(descripcion);
        ciencia.setActivo(activo);

        cienciaDAO.actualizar(ciencia);
        response.sendRedirect(request.getContextPath() + "/ciencias");
    }

    private void eliminarCiencia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cienciaDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/ciencias");
    }
}
