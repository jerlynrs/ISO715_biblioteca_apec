package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.UsuarioDAO;
import org.example.biblioteca_apec.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
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
                    eliminarUsuario(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
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
                insertarUsuario(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarUsuario(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Usuario> listaUsuarios = usuarioDAO.listarTodos();
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.getRequestDispatcher("/views/usuarios/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/usuarios/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.obtenerPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("/views/usuarios/formulario.jsp").forward(request, response);
    }

    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Usuario nuevoUsuario = obtenerUsuarioDesdeRequest(request);
        usuarioDAO.insertar(nuevoUsuario);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Usuario usuario = obtenerUsuarioDesdeRequest(request);
        usuario.setId(Integer.parseInt(request.getParameter("id")));
        usuarioDAO.actualizar(usuario);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private Usuario obtenerUsuarioDesdeRequest(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setCedula(request.getParameter("cedula"));
        usuario.setNoCarnet(request.getParameter("noCarnet"));
        usuario.setTipoPersona(request.getParameter("tipoPersona"));
        usuario.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        return usuario;
    }
}
