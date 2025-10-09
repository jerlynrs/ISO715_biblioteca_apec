package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.EditoraDAO;
import org.example.biblioteca_apec.model.Editora;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EditoraServlet", urlPatterns = {"/editoras"})
public class EditoraServlet extends HttpServlet {

    private EditoraDAO editoraDAO;

    @Override
    public void init() {
        editoraDAO = new EditoraDAO();
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
                    eliminarEditora(request, response);
                    break;
                default:
                    listarEditoras(request, response);
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
                insertarEditora(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarEditora(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarEditoras(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Editora> listaEditoras = editoraDAO.listarTodos();
        request.setAttribute("listaEditoras", listaEditoras);
        request.getRequestDispatcher("/views/editoras/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/editoras/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Editora editora = editoraDAO.obtenerPorId(id);
        request.setAttribute("editora", editora);
        request.getRequestDispatcher("/views/editoras/formulario.jsp").forward(request, response);
    }

    private void insertarEditora(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        Editora nuevaEditora = new Editora();
        nuevaEditora.setDescripcion(descripcion);
        nuevaEditora.setEstado(estado);

        editoraDAO.insertar(nuevaEditora);
        response.sendRedirect(request.getContextPath() + "/editoras");
    }

    private void actualizarEditora(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        Editora editora = new Editora(id, descripcion, estado);
        editoraDAO.actualizar(editora);
        response.sendRedirect(request.getContextPath() + "/editoras");
    }

    private void eliminarEditora(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        editoraDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/editoras");
    }
}
