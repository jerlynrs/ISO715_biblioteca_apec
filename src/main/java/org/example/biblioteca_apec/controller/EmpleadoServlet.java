package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.EmpleadoDAO;
import org.example.biblioteca_apec.model.Empleado;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/empleados"})
public class EmpleadoServlet extends HttpServlet {

    private EmpleadoDAO empleadoDAO;

    @Override
    public void init() {
        empleadoDAO = new EmpleadoDAO();
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
                    eliminarEmpleado(request, response);
                    break;
                default:
                    listarEmpleados(request, response);
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
                insertarEmpleado(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarEmpleado(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Empleado> listaEmpleados = empleadoDAO.listarTodos();
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.getRequestDispatcher("/views/empleados/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado empleado = empleadoDAO.obtenerPorId(id);
        request.setAttribute("empleado", empleado);
        request.getRequestDispatcher("/views/empleados/formulario.jsp").forward(request, response);
    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Empleado nuevoEmpleado = obtenerEmpleadoDesdeRequest(request);
        empleadoDAO.insertar(nuevoEmpleado);
        response.sendRedirect(request.getContextPath() + "/empleados");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Empleado empleado = obtenerEmpleadoDesdeRequest(request);
        empleado.setId(Integer.parseInt(request.getParameter("id")));
        empleadoDAO.actualizar(empleado);
        response.sendRedirect(request.getContextPath() + "/empleados");
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/empleados");
    }

    private Empleado obtenerEmpleadoDesdeRequest(HttpServletRequest request) {
        Empleado empleado = new Empleado();
        empleado.setNombre(request.getParameter("nombre"));
        empleado.setCedula(request.getParameter("cedula"));
        empleado.setTandaLabor(request.getParameter("tandaLabor"));
        empleado.setPorcientoComision(Double.parseDouble(request.getParameter("porcientoComision")));
        empleado.setFechaIngreso(Date.valueOf(request.getParameter("fechaIngreso")));
        empleado.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        return empleado;
    }
}
