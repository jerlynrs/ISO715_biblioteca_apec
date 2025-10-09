package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.EmpleadoDAO;
import org.example.biblioteca_apec.dao.LibroDAO;
import org.example.biblioteca_apec.dao.PrestamoDAO;
import org.example.biblioteca_apec.dao.UsuarioDAO;
import org.example.biblioteca_apec.model.Prestamo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "PrestamoServlet", urlPatterns = {"/prestamos"})
public class PrestamoServlet extends HttpServlet {

    private PrestamoDAO prestamoDAO;
    private EmpleadoDAO empleadoDAO;
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        prestamoDAO = new PrestamoDAO();
        empleadoDAO = new EmpleadoDAO();
        libroDAO = new LibroDAO();
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
                    eliminarPrestamo(request, response);
                    break;
                default:
                    listarPrestamos(request, response);
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
                insertarPrestamo(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarPrestamo(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarPrestamos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Prestamo> listaPrestamos = prestamoDAO.listarTodos();
        request.setAttribute("listaPrestamos", listaPrestamos);
        request.getRequestDispatcher("/views/prestamos/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        cargarDatosFormulario(request);
        request.getRequestDispatcher("/views/prestamos/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Prestamo prestamo = prestamoDAO.obtenerPorId(id);
        request.setAttribute("prestamo", prestamo);
        cargarDatosFormulario(request);
        request.getRequestDispatcher("/views/prestamos/formulario.jsp").forward(request, response);
    }

    private void insertarPrestamo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Prestamo nuevoPrestamo = obtenerPrestamoDesdeRequest(request);
        prestamoDAO.insertar(nuevoPrestamo);
        response.sendRedirect(request.getContextPath() + "/prestamos");
    }

    private void actualizarPrestamo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Prestamo prestamo = obtenerPrestamoDesdeRequest(request);
        prestamo.setId(Integer.parseInt(request.getParameter("id")));
        prestamoDAO.actualizar(prestamo);
        response.sendRedirect(request.getContextPath() + "/prestamos");
    }

    private void eliminarPrestamo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        prestamoDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/prestamos");
    }

    private void cargarDatosFormulario(HttpServletRequest request) throws SQLException {
        request.setAttribute("listaEmpleados", empleadoDAO.listarTodos());
        request.setAttribute("listaLibros", libroDAO.listarTodos());
        request.setAttribute("listaUsuarios", usuarioDAO.listarTodos());
    }

    private Prestamo obtenerPrestamoDesdeRequest(HttpServletRequest request) {
        Prestamo prestamo = new Prestamo();
        prestamo.setEmpleadoId(Integer.parseInt(request.getParameter("empleadoId")));
        prestamo.setLibroId(Integer.parseInt(request.getParameter("libroId")));
        prestamo.setUsuarioId(Integer.parseInt(request.getParameter("usuarioId")));
        prestamo.setFechaPrestamo(Date.valueOf(request.getParameter("fechaPrestamo")));

        String fechaDevolucion = request.getParameter("fechaDevolucion");
        if (fechaDevolucion != null && !fechaDevolucion.isEmpty()) {
            prestamo.setFechaDevolucion(Date.valueOf(fechaDevolucion));
        }

        prestamo.setMontoDia(Double.parseDouble(request.getParameter("montoDia")));
        prestamo.setDiasExcedidos(Integer.parseInt(request.getParameter("diasExcedidos")));
        prestamo.setComentario(request.getParameter("comentario"));
        prestamo.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        return prestamo;
    }
}
