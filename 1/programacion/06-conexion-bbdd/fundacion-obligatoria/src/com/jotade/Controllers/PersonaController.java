package com.jotade.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jotade.Models.Persona;

public class PersonaController {
    public static boolean existsByDni(String dni, Connection conn) throws SQLException {
        String query = "SELECT * FROM personas WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static void save(Persona persona, Connection conn) throws SQLException {
        String query = "INSERT INTO personas (dni, nombre, telefono, tipo) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, persona.getDni());
        ps.setString(2, persona.getNombre());
        ps.setString(3, persona.getTelefono());
        ps.setString(4, persona.getTipo());

        ps.executeUpdate();
    }

    public static void deleteByDni(String dni, Connection conn) throws SQLException {
        String query = "DELETE FROM personas WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);

        ps.executeUpdate();
    }

    public static void deleteByDniAndTipo(String dni, String tipo, Connection conn) throws SQLException {
        String query = "DELETE FROM personas WHERE dni = ? AND tipo = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);
        ps.setString(2, tipo);

        ps.executeUpdate();
    }

    public static void update(Persona persona, Connection conn) throws SQLException {
        String query = "UPDATE personas SET nombre = ?, telefono = ? WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, persona.getNombre());
        ps.setString(2, persona.getTelefono());
        ps.setString(3, persona.getDni());

        ps.executeUpdate();
    }

    public static ArrayList<Persona> getByDni(String dni, Connection conn) throws SQLException {
        String query = "SELECT * FROM personas WHERE dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();
        return getPersonasFromResultSet(rs);
    }

    public static ArrayList<Persona> getDistinct(Connection conn) throws SQLException {
        String query = "SELECT DISTINCT(dni), nombre, telefono, '' AS tipo FROM personas";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getPersonasFromResultSet(rs, conn);
    }

    public static ArrayList<String> getTiposByDni(String dni, Connection conn) throws SQLException {
        String query = "SELECT tipo from personas where dni = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();

        ArrayList<String> tipos = new ArrayList<>();

        while (rs.next()) {
            switch (rs.getString("tipo")) {
                case Persona.CLIENTE -> tipos.add(Persona.CLIENTE);
                case Persona.SOCIO -> tipos.add(Persona.SOCIO);
                case Persona.AUTOR -> tipos.add(Persona.AUTOR);
            }
        }

        return tipos;
    }

    public static ArrayList<Persona> getClientes(Connection conn) throws SQLException {
        String query = "SELECT * FROM personas WHERE tipo = 'cliente'";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getPersonasFromResultSet(rs);
    }

    public static ArrayList<Persona> getSocios(Connection conn) throws SQLException {
        String query = "SELECT * FROM personas WHERE tipo = 'socio'";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getPersonasFromResultSet(rs);
    }

    public static ArrayList<Persona> getAutores(Connection conn) throws SQLException {
        String query = "SELECT * FROM personas WHERE tipo = 'socio'";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getPersonasFromResultSet(rs);
    }

    public static void checkDniAutor(String dni, Connection conn) throws SQLException {
        String query = String.format(
                "SELECT * FROM personas WHERE dni = '%s' AND tipo = 'autor'",
                dni);

        ResultSet rs = conn.createStatement().executeQuery(query);

        if (!rs.next())
            throw new SQLException("", "", 820);
    }

    public static void checkDniCliente(String dni, Connection conn) throws SQLException {
        if (dni != null) {
            String query = String.format(
                    "SELECT * FROM personas WHERE dni = '%s' AND tipo = 'cliente'",
                    dni);
            ResultSet rs = conn.createStatement().executeQuery(query);

            if (!rs.next())
                throw new SQLException("", "", 821);
        }
    }

    public static void checkDniSocio(String dni, Connection conn) throws SQLException {
        if (dni != null) {
            String query = String.format(
                    "SELECT * FROM personas WHERE dni = '%s' AND tipo = 'socio'",
                    dni);

            ResultSet rs = conn.createStatement().executeQuery(query);

            if (!rs.next())
                throw new SQLException("", "", 822);
        }
    }

    private static ArrayList<Persona> getPersonasFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Persona> personas = new ArrayList<>();

        while (rs.next()) {
            personas.add(
                    new Persona(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("tipo")));
        }

        if (personas.size() == 0)
            throw new SQLException("", "", 810);

        return personas;
    }

    public static ArrayList<Persona> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM personas";
        ResultSet rs = conn.createStatement().executeQuery(query);
        return getPersonasFromResultSet(rs, conn);
    }

    private static ArrayList<Persona> getPersonasFromResultSet(ResultSet rs, Connection conn) throws SQLException {
        ArrayList<Persona> ediciones = new ArrayList<>();

        while (rs.next())
            ediciones.add(getPersonaFromResultSet(rs));

        if (ediciones.size() == 0)
            throw new SQLException("", "", 810);

        return ediciones;
    }

    public static Persona getPersonaFromResultSet(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("tipo"));
    }
}
