package com.jotade.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jotade.Models.Revista;

public class RevistaController {
    public static boolean existsByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM revistas WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void save(Revista revista, Connection conn) throws SQLException {
        if (!EdicionController.existsByIsbn(revista.getIsbn(), conn))
            throw new SQLException("", "", 811);

        if (ArticuloController.existsByIsbn(revista.getIsbn(), conn))
            throw new SQLException("", "", 800);

        if (LibroController.existsByIsbn(revista.getIsbn(), conn))
            throw new SQLException("", "", 802);

        String query = "INSERT INTO revistas (isbn, nombre) VALUES (?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, revista.getIsbn());
        ps.setString(2, revista.getNombre());

        ps.executeUpdate();
    }

    public static void deleteByIsbn(String isbn, Connection conn) throws SQLException {
        if (RevistaController.existsByIsbn(isbn, conn))
            RevistaController.deleteByIsbn(isbn, conn);

        String query = "DELETE FROM revistas WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ps.executeUpdate();
    }

    public static void update(Revista revista, Connection conn) throws SQLException {
        String query = "UPDATE revistas SET nombre_revista = ? WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, revista.getNombre());
        ps.setString(2, revista.getIsbn());

        ps.executeUpdate();
    }

    public static Revista getByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM revistas WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();
        return getRevistasFromResultSet(rs, conn).get(0);
    }

    public static ArrayList<Revista> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM revistas";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getRevistasFromResultSet(rs, conn);
    }

    private static Revista getRevistaFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        return new Revista(
                rs.getString("isbn"),
                rs.getString("nombre"));
    }

    private static ArrayList<Revista> getRevistasFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        ArrayList<Revista> articulos = new ArrayList<>();

        while (rs.next())
            articulos.add(getRevistaFromResultSet(rs, conn));

        if (articulos.size() == 0)
            throw new SQLException("", "", 810);

        return articulos;
    }
}
