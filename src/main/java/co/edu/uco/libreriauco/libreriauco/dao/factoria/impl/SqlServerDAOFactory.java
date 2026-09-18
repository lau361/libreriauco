package co.edu.uco.libreriauco.libreriauco.dao.factoria.impl;

import co.edu.uco.libreriauco.libreriauco.dao.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.entidad.SqlServer.DepartamentoSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.entidad.SqlServer.PaisSqlServerDao;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;

import java.sql.Connection;

public class SqlServerDAOFactory extends DaoFactory {


    protected SqlServerDAOFactory(Connection conexion) {
        super(conexion);
    }

    @Override
    protected void abrirConexion() {
        //Tarea como abrir una conexion con sqlServer desde java
        Connection conexion = null;
        setConexion(conexion);
    }

    @Override
    public PaisDAO obtenerPaisDAO() {
        return new PaisSqlServerDao(getConexion());
    }

    @Override
    public DepartamentoDao obtenerDepartamentoDAO() {
        return new DepartamentoSqlServerDao();
    }
}
