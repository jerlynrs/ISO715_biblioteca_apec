package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.TipoBibliografiaDAO;
import org.example.biblioteca_apec.model.TipoBibliografia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tipos-bibliografia")
public class TipoBibliografiaServlet extends HttpServlet {

    private TipoBibliografiaDAO tipoBibliografiaDAO;

    @Override
    public void init() {
        tipoBibliografiaDAO = new TipoBibliografiaDAO();
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
                    eliminarTipo(request, response);
                    break;
                default:
                    listarTipos(request, response);
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
                insertarTipo(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarTipo(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarTipos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<TipoBibliografia> lista = tipoBibliografiaDAO.listarTodos();
        request.setAttribute("listaTipos", lista);
        request.getRequestDispatcher("/views/tipos-bibliografia/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/tipos-bibliografia/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TipoBibliografia tipo = tipoBibliografiaDAO.obtenerPorId(id);
        request.setAttribute("tipo", tipo);
        request.getRequestDispatcher("/views/tipos-bibliografia/formulario.jsp").forward(request, response);
    }

    private void insertarTipo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        boolean activo = request.getParameter("activo") != null;

        TipoBibliografia tipo = new TipoBibliografia();
        tipo.setNombre(nombre);
        tipo.setDescripcion(descripcion);
        tipo.setActivo(activo);

        tipoBibliografiaDAO.insertar(tipo);
        response.sendRedirect(request.getContextPath() + "/tipos-bibliografia");
    }

    private void actualizarTipo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        boolean activo = request.getParameter("activo") != null;

        TipoBibliografia tipo = new TipoBibliografia();
        tipo.setIdTipoBibliografia(id);
        tipo.setNombre(nombre);
        tipo.setDescripcion(descripcion);
        tipo.setActivo(activo);

        tipoBibliografiaDAO.actualizar(tipo);
        response.sendRedirect(request.getContextPath() + "/tipos-bibliografia");
    }

    private void eliminarTipo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tipoBibliografiaDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/tipos-bibliografia");
    }
}
