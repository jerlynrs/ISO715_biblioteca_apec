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

@WebServlet(name = "TipoBibliografiaServlet", urlPatterns = {"/tipos-bibliografia"})
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
                    eliminarTipoBibliografia(request, response);
                    break;
                default:
                    listarTiposBibliografia(request, response);
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
                insertarTipoBibliografia(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarTipoBibliografia(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarTiposBibliografia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<TipoBibliografia> listaTipos = tipoBibliografiaDAO.listarTodos();
        request.setAttribute("listaTipos", listaTipos);
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

    private void insertarTipoBibliografia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        TipoBibliografia nuevoTipo = new TipoBibliografia();
        nuevoTipo.setDescripcion(descripcion);
        nuevoTipo.setEstado(estado);

        tipoBibliografiaDAO.insertar(nuevoTipo);
        response.sendRedirect(request.getContextPath() + "/tipos-bibliografia");
    }

    private void actualizarTipoBibliografia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

        TipoBibliografia tipo = new TipoBibliografia(id, descripcion, estado);
        tipoBibliografiaDAO.actualizar(tipo);
        response.sendRedirect(request.getContextPath() + "/tipos-bibliografia");
    }

    private void eliminarTipoBibliografia(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tipoBibliografiaDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/tipos-bibliografia");
    }
}
