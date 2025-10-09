package org.example.biblioteca_apec.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.biblioteca_apec.dao.*;
import org.example.biblioteca_apec.model.Libro;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LibroServlet", urlPatterns = {"/libros"})
public class LibroServlet extends HttpServlet {

    private LibroDAO libroDAO;
    private TipoBibliografiaDAO tipoBibliografiaDAO;
    private AutorDAO autorDAO;
    private EditoraDAO editoraDAO;
    private CienciaDAO cienciaDAO;
    private IdiomaDAO idiomaDAO;

    @Override
    public void init() {
        libroDAO = new LibroDAO();
        tipoBibliografiaDAO = new TipoBibliografiaDAO();
        autorDAO = new AutorDAO();
        editoraDAO = new EditoraDAO();
        cienciaDAO = new CienciaDAO();
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
                    eliminarLibro(request, response);
                    break;
                default:
                    listarLibros(request, response);
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
                insertarLibro(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarLibro(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarLibros(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Libro> listaLibros = libroDAO.listarTodos();
        request.setAttribute("listaLibros", listaLibros);
        request.getRequestDispatcher("/views/libros/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        cargarDatosFormulario(request);
        request.getRequestDispatcher("/views/libros/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Libro libro = libroDAO.obtenerPorId(id);
        request.setAttribute("libro", libro);
        cargarDatosFormulario(request);
        request.getRequestDispatcher("/views/libros/formulario.jsp").forward(request, response);
    }

    private void insertarLibro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Libro nuevoLibro = obtenerLibroDesdeRequest(request);
        libroDAO.insertar(nuevoLibro);
        response.sendRedirect(request.getContextPath() + "/libros");
    }

    private void actualizarLibro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Libro libro = obtenerLibroDesdeRequest(request);
        libro.setId(Integer.parseInt(request.getParameter("id")));
        libroDAO.actualizar(libro);
        response.sendRedirect(request.getContextPath() + "/libros");
    }

    private void eliminarLibro(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        libroDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/libros");
    }

    private void cargarDatosFormulario(HttpServletRequest request) throws SQLException {
        request.setAttribute("listaTipos", tipoBibliografiaDAO.listarTodos());
        request.setAttribute("listaAutores", autorDAO.listarTodos());
        request.setAttribute("listaEditoras", editoraDAO.listarTodos());
        request.setAttribute("listaCiencias", cienciaDAO.listarTodos());
        request.setAttribute("listaIdiomas", idiomaDAO.listarTodos());
    }

    private Libro obtenerLibroDesdeRequest(HttpServletRequest request) {
        Libro libro = new Libro();
        libro.setDescripcion(request.getParameter("descripcion"));
        libro.setSignaturaTopografica(request.getParameter("signaturaTopografica"));
        libro.setIsbn(request.getParameter("isbn"));
        libro.setTipoBibliografia(Integer.parseInt(request.getParameter("tipoBibliografia")));
        libro.setAutor(Integer.parseInt(request.getParameter("autor")));
        libro.setEditora(Integer.parseInt(request.getParameter("editora")));
        libro.setAnioPublicacion(Integer.parseInt(request.getParameter("anioPublicacion")));
        libro.setCiencia(Integer.parseInt(request.getParameter("ciencia")));
        libro.setIdioma(Integer.parseInt(request.getParameter("idioma")));
        libro.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        return libro;
    }
}
