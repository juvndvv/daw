package com.jotade.Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

import com.jotade.Models.Articulo;

public class ArticuloController {
    public static boolean existsByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM articulos WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void save(Articulo articulo, Connection conn) throws SQLException {
        PersonaController.checkDniAutor(articulo.getDniAutor(), conn);
        PersonaController.checkDniCliente(articulo.getDniCliente(), conn);

        if (!EdicionController.existsByIsbn(articulo.getIsbn(), conn))
            throw new SQLException("", "", 811);

        if (RevistaController.existsByIsbn(articulo.getIsbn(), conn))
            throw new SQLException("", "", 801);

        if (LibroController.existsByIsbn(articulo.getIsbn(), conn))
            throw new SQLException("", "", 802);

        String query = "INSERT INTO articulos (isbn, dni_autor, titulo,  dni_cliente) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, articulo.getIsbn());
        ps.setString(2, articulo.getDniAutor());
        ps.setString(3, articulo.getTitulo());
        ps.setString(4, articulo.getDniCliente());

        ps.executeUpdate();
    }

    public static void deleteByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "DELETE FROM articulos WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ps.executeUpdate();
    }

    public static void update(Articulo articulo, Connection conn) throws SQLException {
        PersonaController.checkDniAutor(articulo.getDniAutor(), conn);
        PersonaController.checkDniCliente(articulo.getDniCliente(), conn);

        String query = "UPDATE articulos SET dni_autor = ?, titulo = ?, dni_cliente = ? WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, articulo.getDniAutor());
        ps.setString(2, articulo.getTitulo());
        ps.setString(3, articulo.getDniCliente());
        ps.setString(4, articulo.getIsbn());

        ps.executeUpdate();
    }

    public static Articulo getByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM articulos WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();

        return getArticulosFromResultSet(rs, conn).get(0);
    }

    public static ArrayList<Articulo> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM articulos";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getArticulosFromResultSet(rs, conn);
    }

    public static ArrayList<Articulo> getVendidos(Connection conn) throws SQLException {
        String query = "SELECT * FROM articulos WHERE dni_cliente IS NOT NULL";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getArticulosFromResultSet(rs, conn);
    }

    public static ArrayList<Articulo> getNoVendidos(Connection conn) throws SQLException {
        String query = "SELECT * FROM articulos WHERE dni_cliente IS NULL";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getArticulosFromResultSet(rs, conn);
    }

    private static Articulo getArticuloFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        return new Articulo(
                rs.getString("isbn"),
                rs.getString("dni_autor"),
                rs.getString("titulo"),
                rs.getString("dni_cliente"));
    }

    private static ArrayList<Articulo> getArticulosFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();

        while (rs.next())
            articulos.add(getArticuloFromResultSet(rs, conn));

        if (articulos.size() == 0)
            throw new SQLException("", "", 810);

        return articulos;
    }
}
