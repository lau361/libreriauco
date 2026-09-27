package co.edu.uco.libreriauco.libreriauco.dao.factoria.impl;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer.CiudadSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer.DepartamentoSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer.PaisSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerDAOFactory extends DaoFactory {
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=Doo;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "javaUser";
    private static final String CLAVE = "1040874176";

    @Override
    protected void abrirConexion() {
         //como abrir la conexion con sql server desde java
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            setConexion(conexion);
        } catch (SQLException e) {
            throw new IllegalArgumentException("No se pudo conectar a SQL Server", e);
        }
    }

    @Override
    public PaisDAO obtenerPaisDAO() {
        return new PaisSqlServerDao(getConexion());
    }

    @Override
    public DepartamentoDao obtenerDepartamentoDAO() {
        return new DepartamentoSqlServerDao(getConexion());
    }

    @Override
    public CiudadDao obtenerCiudadDAO() {
        return new CiudadSqlServerDao(getConexion());
    }
}
