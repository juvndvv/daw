package com.jotade.Database;

import java.sql.Statement;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Connector {
    public Connection CONN;

    public final String DRIVER = "org.mariadb.jdbc.Driver";
    public String HOST;
    public String PORT;
    public String URL;
    public String DATABASE;
    public String USERNAME;
    public String PASSWORD;

    public Connection getConnection(String configFile) {
        try {
            loadProperties(configFile);
            setLogOff();
            checkDatabase();
            CONN = DriverManager.getConnection(URL + "/" + DATABASE, USERNAME, PASSWORD);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuracion");

        } catch (SQLNonTransientConnectionException e) {
            System.out.println("El servidor no responde");
        }

        catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }

        return CONN;
    }

    public void loadProperties(String configFile) throws IOException {
        Properties p = new Properties();
        p.load(new FileReader(configFile));

        HOST = p.getProperty("db.host");
        PORT = p.getProperty("db.port");
        DATABASE = "fundacion";
        URL = String.format("jdbc:mariadb://%s:%s", HOST, PORT);
        USERNAME = readUsername();
        PASSWORD = readPassword();
    }

    public String readUsername() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Usuario: ");
        return reader.nextLine();
    }

    public String readPassword() {
        Console console = System.console();
        char[] passwordArray = console.readPassword("Contraseña: ");
        return new String(passwordArray);
    }

    public void checkDatabase() throws SQLException, FileNotFoundException, IOException {
        CONN = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        if (!existsDatabase()) {
            System.out.println("La base de datos no existe, creando una nueva...");
            createDatabase();
            System.out.println("Base de datos creada!");

            System.out.println("Creando las tablas...");
            createTables();
            System.out.println("Tablas creadas!");
        }
    }

    public boolean existsDatabase() throws SQLException {
        String query = "SHOW DATABASES LIKE '" + DATABASE + "'";
        ResultSet rs = CONN.createStatement().executeQuery(query);
        return rs.next();
    }

    public void createDatabase() throws SQLException {
        String query = "CREATE DATABASE " + DATABASE;
        CONN.createStatement().executeUpdate(query);
    }

    public void createTables() throws FileNotFoundException, IOException, SQLException {
        Statement statement = CONN.createStatement();
        statement.execute("USE " + DATABASE);

        // Crear tabla ediciones
        String createTableEdiciones = "CREATE TABLE ediciones ("
                + "isbn VARCHAR(13) PRIMARY KEY,"
                + "fecha_publicacion DATE NOT NULL"
                + ")";
        statement.executeUpdate(createTableEdiciones);
        System.out.println("La tabla ediciones se ha creado correctamente.");

        // Crear tabla personas
        String createTablePersonas = "CREATE TABLE personas ("
                + "dni VARCHAR(9),"
                + "nombre VARCHAR(40) NOT NULL,"
                + "telefono VARCHAR(9) NOT NULL,"
                + "tipo VARCHAR(7),"
                + "PRIMARY KEY(dni, tipo)"
                + ")";
        statement.executeUpdate(createTablePersonas);
        System.out.println("La tabla personas se ha creado correctamente.");

        // Crear tabla articulos
        String createTableArticulos = "CREATE TABLE articulos ("
                + "isbn VARCHAR(13) PRIMARY KEY,"
                + "dni_autor VARCHAR(9) NOT NULL,"
                + "titulo VARCHAR(30) NOT NULL,"
                + "dni_cliente VARCHAR(9),"
                + "FOREIGN KEY (isbn) REFERENCES ediciones(isbn),"
                + "FOREIGN KEY (dni_autor) REFERENCES personas(dni),"
                + "FOREIGN KEY (dni_cliente) REFERENCES personas(dni)"
                + ")";
        statement.executeUpdate(createTableArticulos);
        System.out.println("La tabla articulos se ha creado correctamente.");

        // Crear tabla libros
        String createTableLibros = "CREATE TABLE libros ("
                + "isbn VARCHAR(13) PRIMARY KEY,"
                + "dni_autor VARCHAR(9) NOT NULL,"
                + "titulo VARCHAR(30) NOT NULL,"
                + "dni_socio VARCHAR(9),"
                + "FOREIGN KEY (isbn) REFERENCES ediciones(isbn),"
                + "FOREIGN KEY (dni_autor) REFERENCES personas(dni),"
                + "FOREIGN KEY (dni_socio) REFERENCES personas(dni)"
                + ")";
        statement.executeUpdate(createTableLibros);
        System.out.println("La tabla libros se ha creado correctamente.");

        // Crear tabla revistas
        String createTableRevistas = "CREATE TABLE revistas ("
                + "isbn VARCHAR(13) PRIMARY KEY,"
                + "nombre VARCHAR(20) NOT NULL,"
                + "FOREIGN KEY (isbn) REFERENCES ediciones(isbn)"
                + ")";
        statement.executeUpdate(createTableRevistas);
        System.out.println("La tabla revistas se ha creado correctamente.");

        // Crear tabla ventas_revistas
        String createTableVentasRevistas = "CREATE TABLE ventas_revistas ("
                + "isbn VARCHAR(13) PRIMARY KEY,"
                + "dni_cliente VARCHAR(9) NOT NULL,"
                + "ejemplares INTEGER NOT NULL,"
                + "FOREIGN KEY (isbn) REFERENCES ediciones(isbn),"
                + "FOREIGN KEY (dni_cliente) REFERENCES personas(dni)"
                + ")";
        statement.executeUpdate(createTableVentasRevistas);
        System.out.println("La tabla ventas_revistas se ha creado correctamente.");
    }

    public void setLogOff() {
    }
}