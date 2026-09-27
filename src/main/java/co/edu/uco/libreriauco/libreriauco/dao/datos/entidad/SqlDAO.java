package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilSql;

import java.sql.Connection;

public abstract class SqlDAO {
    private Connection conexion;

    protected SqlDAO(Connection conexion) {
        setConexion(conexion);
    }
    private void setConexion(Connection conexion) {
        UtilSql.asegurarConexionAbierta(conexion);
        this.conexion = conexion;
    }
    protected Connection getConexion() {
        return conexion;
    }
}
