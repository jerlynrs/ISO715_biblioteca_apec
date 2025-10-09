package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.AutorDAO;
import org.example.biblioteca_apec.dao.IdiomaDAO;
import org.example.biblioteca_apec.model.Autor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AutorServlet", urlPatterns = {"/autores"})
public class AutorServlet extends HttpServlet {

    private AutorDAO autorDAO;
    private IdiomaDAO idiomaDAO;

    @Override
    public void init() {
        autorDAO = new AutorDAO();
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
        List<Autor> listaAutores = autorDAO.listarTodos();
        request.setAttribute("listaAutores", listaAutores);
        request.getRequestDispatcher("/views/autores/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        request.setAttribute("listaIdiomas", idiomaDAO.listarTodos());
        request.getRequestDispatcher("/views/autores/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Autor autor = autorDAO.obtenerPorId(id);
        request.setAttribute("autor", autor);
        request.setAttribute("listaIdiomas", idiomaDAO.listarTodos());
        request.getRequestDispatcher("/views/autores/formulario.jsp").forward(request, response);
    }

    private void insertarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Autor nuevoAutor = obtenerAutorDesdeRequest(request);
        autorDAO.insertar(nuevoAutor);
        response.sendRedirect(request.getContextPath() + "/autores");
    }

    private void actualizarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Autor autor = obtenerAutorDesdeRequest(request);
        autor.setId(Integer.parseInt(request.getParameter("id")));
        autorDAO.actualizar(autor);
        response.sendRedirect(request.getContextPath() + "/autores");
    }

    private void eliminarAutor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        autorDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/autores");
    }

    private Autor obtenerAutorDesdeRequest(HttpServletRequest request) {
        Autor autor = new Autor();
        autor.setNombre(request.getParameter("nombre"));
        autor.setPaisOrigen(request.getParameter("paisOrigen"));
        autor.setIdiomaNativo(Integer.parseInt(request.getParameter("idiomaNativo")));
        autor.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        return autor;
    }
}
