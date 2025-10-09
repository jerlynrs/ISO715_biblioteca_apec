package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.IdiomaDAO;
import org.example.biblioteca_apec.model.Idioma;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IdiomaServlet", urlPatterns = {"/idiomas"})
public class IdiomaServlet extends HttpServlet {

    private IdiomaDAO idiomaDAO;

    @Override
    public void init() {
        idiomaDAO = new IdiomaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "listar";
            }

            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarIdioma(request, response);
                    break;
                default:
                    listarIdiomas(request, response);
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
                insertarIdioma(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarIdioma(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarIdiomas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Idioma> listaIdiomas = idiomaDAO.listarTodos();
        request.setAttribute("listaIdiomas", listaIdiomas);
        request.getRequestDispatcher("/views/idiomas/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/idiomas/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Idioma idioma = idiomaDAO.obtenerPorId(id);
        request.setAttribute("idioma", idioma);
        request.getRequestDispatcher("/views/idiomas/formulario.jsp").forward(request, response);
    }

    private void insertarIdioma(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        Idioma nuevoIdioma = new Idioma();
        nuevoIdioma.setDescripcion(descripcion);
        nuevoIdioma.setEstado(estado);

        idiomaDAO.insertar(nuevoIdioma);
        response.sendRedirect(request.getContextPath() + "/idiomas");
    }

    private void actualizarIdioma(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        Idioma idioma = new Idioma(id, descripcion, estado);
        idiomaDAO.actualizar(idioma);
        response.sendRedirect(request.getContextPath() + "/idiomas");
    }

    private void eliminarIdioma(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        idiomaDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/idiomas");
    }
}
