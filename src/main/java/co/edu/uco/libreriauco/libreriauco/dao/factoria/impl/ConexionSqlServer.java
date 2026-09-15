package co.edu.uco.libreriauco.libreriauco.dao.factoria.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSqlServer {

    public static void main(String[] args) {

        String url = "jdbc:sqlserver://DESKTOP-JP7VRTL:1433;databaseName=TU_BASE_DE_DATOS;trustServerCertificate=true";
        String usuario = "javaUser";
        String contraseña = "TU_CONTRASEÑA";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("¡Conexión exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}