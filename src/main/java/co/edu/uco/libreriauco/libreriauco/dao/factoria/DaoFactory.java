package co.edu.uco.libreriauco.libreriauco.dao.factoria;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilSql;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoFactory {
    private Connection conexion;

    protected DaoFactory() {
        abrirConexion();
    }

     protected Connection getConexion() {
        return conexion;
    }


    protected void setConexion(Connection conexion) {

        // Verificamos que la conexión exista
        if (conexion == null) {
            throw new IllegalArgumentException("La conexión no puede ser nula");
        }

        // Verificamos que la conexión esté abierta
        try {
            if (conexion.isClosed()) {
                throw new IllegalArgumentException("La conexión está cerrada");
            }
            //entra al chatch cuando no se pudo comprobrar que la conexion estaba abierta o cerrada
        } catch (SQLException e) {
            throw new IllegalArgumentException("No se pudo comprobar la conexión", e);
        }

        // Si llegó hasta aquí, la conexión es válida
        this.conexion = conexion;
    }
    //abstrat para que cada base de datos lo implemente de forma diferente
    protected abstract void abrirConexion();

    public void cerrarConexion() {
        UtilSql.cerrarConexion(conexion);
    }

    public void iniciarTransaccion() {
      UtilSql.iniciarTransaccion(conexion);
     }

     public void confirmarTransaccion() {
      UtilSql.confirmarTransaccion(conexion);
     }
public void cancelarTransaccion() {
      UtilSql.cancelarTransaccion(conexion);
}
    // va a fabricar los daos para las entidades
    public abstract PaisDAO obtenerPaisDAO();
    public abstract DepartamentoDao obtenerDepartamentoDAO();
    public abstract CiudadDao obtenerCiudadDAO();

}
