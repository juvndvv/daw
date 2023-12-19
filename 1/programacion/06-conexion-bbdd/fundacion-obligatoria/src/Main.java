
import java.sql.Connection;
import java.sql.SQLException;

import com.jotade.Database.Connector;
import com.jotade.Views.GlobalMenu;

public class Main {
    public static void main(String[] args) {
        Connection conn = new Connector().getConnection("config.properties");

        if (conn != null) {
            GlobalMenu menu = new GlobalMenu(conn);
            menu.start();

            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error cerrando la conexion");
            }
        } else {
            System.out.println("No se ha podido establecer la conexion");
        }
    }
}
