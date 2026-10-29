package co.edu.uco.libreriauco.libreriauco.dao.factoria.impl;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer.CiudadSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer.DepartamentoSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer.PaisSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlServerDAOFactory extends DaoFactory {
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=Doo;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "javaUser";
    private static final String CLAVE = System.getenv("LIBRERIAUCO_DB_CLAVE");

    private static final HikariDataSource POOL = crearPool();
    private static HikariDataSource crearPool() {
        HikariConfig configuracion = new HikariConfig();
        configuracion.setJdbcUrl(URL);
        configuracion.setUsername(USUARIO);
        configuracion.setPassword(CLAVE);
        configuracion.setMaximumPoolSize(10);
        configuracion.setInitializationFailTimeout(-1);
        return new HikariDataSource(configuracion);
    }


    @Override
    protected void abrirConexion() {
         //como abrir la conexion con sql server desde java
        try {
            Connection conexion = POOL.getConnection();
            setConexion(conexion);
        } catch (SQLException e) {
            throw  LibreriaUcoDatosExcepcion.crear( "No se pudo conectar a SQL Server", e.getMessage(),e);
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
