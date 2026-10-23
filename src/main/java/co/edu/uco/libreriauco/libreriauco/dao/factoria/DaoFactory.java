package co.edu.uco.libreriauco.libreriauco.dao.factoria;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.enums.FactoriaEnum;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.impl.SqlServerDAOFactory;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilSql;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoFactory {
    private Connection conexion;
    private static FactoriaEnum factoria = FactoriaEnum.SQLSERVER;


    protected DaoFactory() {
        abrirConexion();
    }

    public static DaoFactory obtenerFactoria(){
        switch (factoria){
            case SQLSERVER :
                return  new SqlServerDAOFactory();
            default:
                var mensaje = CatalogoMensajes.DaoFactory.USUARIO_ERROR_FUENTE_INFORMACION_NO_HABILITADA;
                throw LibreriaUcoDatosExcepcion.crear(mensaje);
        }
    }

     protected Connection getConexion() {
        return conexion;
    }


    protected void setConexion(Connection conexion) {

        if (conexion == null) {
            var mensaje = CatalogoMensajes.DaoFactory.USUARIO_ERROR_CONEXION_NULA;
            throw LibreriaUcoDatosExcepcion.crear(mensaje);
        }
        try {
            if (conexion.isClosed()) {
                var mensaje = CatalogoMensajes.DaoFactory.USUARIO_ERROR_CONEXION_CERRADA;
                throw LibreriaUcoDatosExcepcion.crear(mensaje);
            }
        } catch (SQLException e) {
            var mensaje = CatalogoMensajes.DaoFactory.USUARIO_ERROR_PROBLEMA_VALIDANDO_CONEXION;
            throw LibreriaUcoDatosExcepcion.crear(mensaje , e.getMessage(), e);
        }
        this.conexion = conexion;
    }

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
