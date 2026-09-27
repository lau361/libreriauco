package co.edu.uco.libreriauco.libreriauco.transversal.Utilitario;

import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoTransversalExcepcion;

import java.sql.Connection;
import java.sql.SQLException;

public class UtilSql {
    private UtilSql(){}
    public static boolean conexionEstaAbierta(Connection conexion){
        try{
            return (!conexionEstaVacia(conexion) && !conexion.isClosed());
         // se mira la concreta o si no la general
        } catch (SQLException exception){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA;

            //nosotros ya tenemos una excepcion definida para esta capa que es la transversal
            // voy a lanzar esa excepcion para eso trown

            //el mensaje tecnico lo saco de la propia excepcion donde se presento el problema
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario,exception.getMessage() , exception);

        } catch (Exception exception){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario,exception.getMessage() , exception);

        }
    }

    //se va a crear un metodo generico
    public static void iniciarTransaccion(Connection conexion){
        //la conexion debe estar abierta y la transaccion no puede estar iniciada
        if(transaccionEstaIniciada(conexion)){
            //si la transaccion ya esta iniciada no se puede hacer nada ent voy a reportar un problema
            var mensajeUsuario= CatalogoMensajes.UtilSql.USUARIO_ERROR_NO_ES_POSIBLE_INICIAR_TRANSACCION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }
        //aqui es la tarea de como iniciar la transaccion


    }
    public static void confirmarTransaccion(Connection conexion){
        //si la transaccion no esta iniciada error
        if(!transaccionEstaIniciada(conexion)){
            var mensajeUsuario= "Mensaje de error por que no es posible confirmar una transaccion que no fue iniciada";
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }
        //aqui es la tarea de como confirmar la transaccion
    }
    public static void cancelarTransaccion(Connection conexion){
        //si la transaccion no esta iniciada error ya que no se puede cancelar algo que no se inicio
        if(!transaccionEstaIniciada(conexion)){
            var mensajeUsuario= "Mensaje de error por que no es posible cancelar una transaccion que no fue iniciada";
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }
        //aqui es la tarea de como cancelarlar la transaccion
    }
    public static void cerrarConexion(Connection conexion){
        //si la conexion no esta abierta ,no se puede cerrar
        if(!conexionEstaAbierta(conexion)){
            var mensajeUsuario= "Mensaje de error por que no es posible cerrar una conexion que no esta abierta";
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }
        //aqui es la tarea de como cerrar la conexion
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

    public static boolean transaccionEstaIniciada(Connection conexion){
        try {
            return conexionEstaAbierta(conexion) && !conexion.getAutoCommit();
        }catch (SQLException exception){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario,exception.getMessage() , exception);

        } catch (Exception exception){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario,exception.getMessage() , exception);

        }

    }

    public static boolean conexionEstaVacia(Connection conexion){
        return UtilObjeto.esNulo(conexion);
    }

}
