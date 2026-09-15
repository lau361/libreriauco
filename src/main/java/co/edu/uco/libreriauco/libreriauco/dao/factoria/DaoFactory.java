package co.edu.uco.libreriauco.libreriauco.dao.factoria;

import co.edu.uco.libreriauco.libreriauco.dao.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.entidad.PaisDAO;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoFactory {
    private Connection conexion;

    protected DaoFactory(Connection conexion) {
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

        // Primero verificamos que exista una conexión
        if (conexion != null) {

            try {
                // Cerramos la conexión
                conexion.close();

            } catch (SQLException e) {
                // Si ocurre un error al cerrarla
                throw new IllegalArgumentException("No se pudo cerrar la conexión", e);
            }
        }
    }


    public void iniciarTransaccion() {

        try {
         // Desactivamos el guardado automático para controlar
         // cuándo se confirman o se deshacen los cambios
          conexion.setAutoCommit(false);

        } catch (SQLException e) {
        // Si no podemos desactivar el guardado automático,
        // no podemos iniciar correctamente la transacción
        throw new IllegalArgumentException("No se pudo iniciar la transacción", e);
       }
     }
     public void confirmarTransaccion() {

    try {
        // Confirmamos todos los cambios realizados
        // durante la transacción
        conexion.commit();

    } catch (SQLException e) {
        // Si no podemos confirmar los cambios,
        // la transacción no se pudo confirmar
        throw new IllegalArgumentException(
                "No se pudo confirmar la transacción", e);
     }
     }
public void cancelarTransaccion() {

    try {
        // Deshacemos todos los cambios realizados
        // durante la transacción
        conexion.rollback();

    } catch (SQLException e) {
        // Si no podemos deshacer los cambios,
        // la transacción no se pudo cancelar
        throw new IllegalArgumentException(
                "No se pudo cancelar la transacción", e);
    }
}
    // va a fabricar los daos para las entidades
    public abstract PaisDAO obtenerPaisDAO();
    public abstract DepartamentoDao obtenerDepartamentoDAO();

}
