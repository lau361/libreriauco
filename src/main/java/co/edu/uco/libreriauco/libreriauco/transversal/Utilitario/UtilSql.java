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

    public static void asegurarConexionAbierta(Connection conexion){
        //si la conexion no esta abierta , voy a reportar un problema
        if(!conexionEstaAbierta(conexion)){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_CONEXION_SQL_NO_ESTA_ABIERTA;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }
    }

    //se va a crear un metodo generico
    public static void iniciarTransaccion(Connection conexion){
        //la conexion debe estar abierta y la transaccion no puede estar iniciada
        // si la transacion esta iniciada ent manda un error
        if(transaccionEstaIniciada(conexion) || !conexionEstaAbierta(conexion)){
            //si la transaccion ya esta iniciada no se puede hacer nada ent voy a reportar un problema
            var mensajeUsuario= CatalogoMensajes.UtilSql.USUARIO_ERROR_NO_ES_POSIBLE_INICIAR_TRANSACCION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        } else{
            // si no ent se comienza la transaccion
            try {
                // Desactivamos el guardado automático para controlar
                // cuándo se confirman o se deshacen los cambios
                conexion.setAutoCommit(false);

            } catch (SQLException exception) {
                // Si no podemos desactivar el guardado automático,
                // no podemos iniciar correctamente la transacción
                var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_INICIANDO_TRANSACCION_SQL;
                throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
            catch (Exception exception) {
                // Si ocurre un error al iniciar la transacción
                var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_INICIANDO_TRANSACCION_SQL;
                throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
        }
    }

    public static void confirmarTransaccion(Connection conexion){
        //si la transaccion no esta iniciada error
        if(!transaccionEstaIniciada(conexion)){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_NO_ES_POSIBLE_CONFIRMAR_TRANSACCION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }else{
            try {
                conexion.commit();
            } catch (SQLException exception) {
                var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_COMFIRMANDO_TRANSACCION_SQL;
                throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
            catch (Exception exception) {
                var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONFIRMANDO_TRANSACCION_SQL;
                throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
        }
    }
    public static void cancelarTransaccion(Connection conexion){
        //si la transaccion no esta iniciada error ya que no se puede cancelar algo que no se inicio
        if(!transaccionEstaIniciada(conexion)){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_NO_ES_POSIBLE_CANCELAR_TRANSACCION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }else{
            try {
                conexion.rollback();
            } catch (SQLException exception) {
                var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_CANCELANDO_TRANSACCION_SQL;
                throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
            catch (Exception exception) {
                var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CANCELANDO_TRANSACCION_SQL;
                throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
        }
    }

    public static void cerrarConexion(Connection conexion){
        //si la conexion no esta abierta ,no se puede cerrar
        if(!conexionEstaAbierta(conexion)){
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_NO_ES_POSIBLE_CERRAR_CONEXION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario);
        }
        // Primero verificamos que exista una conexión
        try {
            // Cerramos la conexión
            conexion.close();
        } catch (SQLException exception) {
                // Si ocurre un error al cerrarla
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_CERRANDO_CONEXION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
            }
        catch (Exception exception) {
            // Si ocurre un error al cerrarla
            var mensajeUsuario = CatalogoMensajes.UtilSql.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CERRANDO_CONEXION_SQL;
            throw LibreriaUcoTransversalExcepcion.crear(mensajeUsuario, exception.getMessage(), exception);
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
