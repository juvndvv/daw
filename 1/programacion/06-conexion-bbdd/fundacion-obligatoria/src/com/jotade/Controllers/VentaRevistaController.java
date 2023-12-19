package com.jotade.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jotade.Models.VentaRevista;

public class VentaRevistaController {
    public static boolean existsByIsbn(String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM ventas_revistas WHERE isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static boolean existsByDni(String dni, Connection conn) throws SQLException {
        String query = "SELECT * FROM ventas_revistas WHERE dni_cliente = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static boolean existsByDniAndIsbn(String dni, String isbn, Connection conn) throws SQLException {
        String query = "SELECT * FROM ventas_revistas WHERE dni_cliente = ? AND isbn = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);
        ps.setString(2, isbn);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void save(VentaRevista ventaRevista, Connection conn) throws SQLException {
        if (!existsByDniAndIsbn(ventaRevista.getDniCliente(), ventaRevista.getIsbn(), conn)) {
            PersonaController.checkDniCliente(ventaRevista.getDniCliente(), conn);

            String query = "INSERT INTO ventas_revistas (isbn, dni_cliente, ejemplares)  VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ventaRevista.getIsbn());
            ps.setString(2, ventaRevista.getDniCliente());
            ps.setInt(3, ventaRevista.getEjemplares());

            ps.executeUpdate();

        } else {
            String query = "SELECT ejemplares FROM ventas_revistas WHERE dni_cliente = ? AND isbn = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ventaRevista.getDniCliente());
            ps.setString(2, ventaRevista.getIsbn());

            ResultSet rs = ps.executeQuery();
            rs.next();

            int ejemplares = rs.getInt("ejemplares");
            ventaRevista.setEjemplares(ventaRevista.getEjemplares() + ejemplares);
            update(ventaRevista, conn);
        }
    }

    public static void deleteByIsbnAndDni(String isbn, String dni, Connection conn) throws SQLException {
        String query = "DELETE FROM ventas_revistas WHERE isbn = ? AND dni_cliente = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, isbn);
        ps.setString(2, dni);

        ps.executeUpdate();
    }

    public static void update(VentaRevista ventaRevista, Connection conn) throws SQLException {
        PersonaController.checkDniCliente(ventaRevista.getDniCliente(), conn);

        String query = "UPDATE ventas_revistas SET ejemplares = ? WHERE isbn = ? AND dni_cliente = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, ventaRevista.getEjemplares());
        ps.setString(2, ventaRevista.getIsbn());
        ps.setString(3, ventaRevista.getDniCliente());

        ps.executeUpdate();
    }

    public static ArrayList<VentaRevista> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM ventas_revistas";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getRevistasFromResultSet(rs, conn);
    }

    private static VentaRevista getVentaRevistaFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        return new VentaRevista(
                rs.getString("isbn"),
                rs.getString("dni_cliente"),
                rs.getInt("ejemplares"));
    }

    private static ArrayList<VentaRevista> getRevistasFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        ArrayList<VentaRevista> articulos = new ArrayList<>();

        while (rs.next())
            articulos.add(getVentaRevistaFromResultSet(rs, conn));

        if (articulos.size() == 0)
            throw new SQLException("", "", 810);

        return articulos;
    }
}
