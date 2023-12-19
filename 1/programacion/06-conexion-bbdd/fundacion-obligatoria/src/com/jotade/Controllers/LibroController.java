package com.jotade.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jotade.Models.Libro;

public class LibroController {
    public static boolean existsByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM libros WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void save(Libro libro, Connection conn) throws SQLException {
        PersonaController.checkDniAutor(libro.getDniAutor(), conn);
        PersonaController.checkDniSocio(libro.getDniSocio(), conn);

        if (!EdicionController.existsByIsbn(libro.getIsbn(), conn))
            throw new SQLException("", "", 811);

        if (RevistaController.existsByIsbn(libro.getIsbn(), conn))
            throw new SQLException("", "", 801);

        if (ArticuloController.existsByIsbn(libro.getIsbn(), conn))
            throw new SQLException("", "", 800);

        String query = "INSERT INTO libros (isbn, dni_autor, titulo, dni_socio) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, libro.getIsbn());
        ps.setString(2, libro.getDniAutor());
        ps.setString(3, libro.getTitulo());
        ps.setString(4, libro.getDniSocio());

        ps.executeUpdate();
    }

    public static void deleteByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "DELETE FROM libros WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ps.executeUpdate();
    }

    public static void update(Libro libro, Connection conn) throws SQLException {
        PersonaController.checkDniAutor(libro.getDniAutor(), conn);
        PersonaController.checkDniSocio(libro.getDniSocio(), conn);

        String query = "UPDATE libros SET dni_autor = ?, titulo = ?, dni_socio = ? WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, libro.getDniAutor());
        ps.setString(2, libro.getTitulo());
        ps.setString(3, libro.getDniSocio());
        ps.setString(4, libro.getIsbn());

        ps.executeUpdate();
    }

    public static Libro getByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM libros WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();

        if (!rs.next())
            throw new SQLException(isbn, query, 810);

        return new Libro(
                isbn,
                rs.getString("dni_autor"),
                rs.getString("titulo"),
                rs.getString("dni_socio"));
    }

    public static ArrayList<Libro> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM libros";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getLibrosFromResultSet(rs, conn);
    }

    public static ArrayList<Libro> getNoPrestados(Connection conn) throws SQLException {
        String query = "SELECT * FROM libros WHERE dni_socio IS null";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getLibrosFromResultSet(rs, conn);
    }

    public static ArrayList<Libro> getPrestados(Connection conn) throws SQLException {
        String query = "SELECT * FROM libros WHERE dni_socio IS NOT null";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getLibrosFromResultSet(rs, conn);
    }

    private static ArrayList<Libro> getLibrosFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        ArrayList<Libro> ediciones = new ArrayList<>();

        while (rs.next())
            ediciones.add(getLibroFromResultSet(rs, conn));

        if (ediciones.size() == 0)
            throw new SQLException("", "", 810);

        return ediciones;
    }

    public static Libro getLibroFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        String isbn = rs.getString("isbn");
        return new Libro(
                isbn,
                rs.getString("dni_autor"),
                rs.getString("titulo"),
                rs.getString("dni_socio"));
    }
}
