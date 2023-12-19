package com.jotade.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jotade.Models.Edicion;

public class EdicionController {
    public static boolean existsByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM ediciones WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void save(Edicion edicion, Connection conn) throws SQLException {
        String query = "INSERT INTO ediciones (isbn, fecha_publicacion) VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, edicion.getIsbn());
        ps.setDate(2, edicion.getFechaPublicacion());

        ps.executeUpdate();
    }

    public static void deleteByIsbn(String isbn, Connection conn) throws SQLException {
        if (ArticuloController.existsByIsbn(isbn, conn))
            ArticuloController.deleteByIsbn(isbn, conn);

        else if (RevistaController.existsByIsbn(isbn, conn))
            RevistaController.deleteByIsbn(isbn, conn);

        else if (LibroController.existsByIsbn(isbn, conn))
            LibroController.deleteByIsbn(isbn, conn);

        String query = "DELETE FROM ediciones WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ps.executeUpdate();
    }

    public static void update(Edicion edicion, Connection conn) throws SQLException {
        String query = "UPDATE ediciones SET fecha_publicacion = ? WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDate(1, edicion.getFechaPublicacion());
        ps.setString(2, edicion.getIsbn());

        ps.executeUpdate();
    }

    public static Edicion getByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM ediciones WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();

        if (!rs.next())
            throw new SQLException("", "", 810);

        return new Edicion(
                isbn,
                rs.getDate("fecha_publicacion"));
    }

    public static ArrayList<Edicion> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM ediciones";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getEdicionesFromResultSet(rs, conn);
    }

    private static ArrayList<Edicion> getEdicionesFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        ArrayList<Edicion> ediciones = new ArrayList<>();

        while (rs.next())
            ediciones.add(getEdicionFromResultSet(rs));

        if (ediciones.size() == 0)
            throw new SQLException("", "", 810);

        return ediciones;
    }

    public static Edicion getEdicionFromResultSet(ResultSet rs) throws SQLException {
        return new Edicion(
                rs.getString("isbn"),
                rs.getDate("fecha_publicacion"));
    }
}
