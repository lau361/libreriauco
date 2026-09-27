package co.edu.uco.libreriauco.libreriauco.dao.factoria.impl;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.impl.SqlServerDAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSqlServer {

    public static void main(String[] args) {

        SqlServerDAOFactory factory = new SqlServerDAOFactory();

        System.out.println("¡Conexión exitosa!");

        factory.cerrarConexion();
    }
}