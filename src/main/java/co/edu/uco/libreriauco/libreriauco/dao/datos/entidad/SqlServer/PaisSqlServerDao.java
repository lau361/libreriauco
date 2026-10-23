package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaisSqlServerDao extends SqlDAO implements PaisDAO {
    //aqui estoy diciendo este logger es el de mi dao pais
    private static final Logger Logger_ = LoggerFactory.getLogger(PaisSqlServerDao.class);

    public PaisSqlServerDao(Connection conexion) {
        super(conexion);
    }

    @Override
    public void actualizar(UUID uuid, PaisEntidad entidad) {
        var sentenciaSql ="update pais set nombre =? where id=?";
        try(var sentencia= getConexion().prepareStatement(sentenciaSql)){
            sentencia.setString(1, entidad.getNombre());
            sentencia.setObject(2,uuid);
            sentencia.executeUpdate();
        }catch (SQLException excepcion){
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PAIS;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
            //el controlado
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PAIS;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
        }
    }

    //bn
    @Override
    public PaisEntidad consultarPorId(UUID uuid) {
        var sentenciaSql="select id, nombre from pais where id=?";
        var PaisEncontrado= new PaisEntidad.Builder().build();
        Logger_.info("Se inicio la consulta del pais con id {}", uuid);
        try(var sentencia= getConexion().prepareStatement(sentenciaSql)){
            sentencia.setObject(1,uuid);
            var resultado = sentencia.executeQuery();
            if(resultado.next()){
                PaisEncontrado = new PaisEntidad.Builder().id(UUID.fromString(resultado.getString("id"))).nombre(resultado.getString("nombre")).build();
                Logger_.info("Pais encontrado : {}", PaisEncontrado.getNombre());
            }else{
                Logger_.warn("No se encontro ningun pais con id {}", uuid);
            }
        }catch (SQLException excepcion){
            Logger_.error("Error de conexion con la base de datos consultando el pais con id {}", uuid, excepcion);
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PAIS_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
            //el controlado
        } catch (Exception excepcion) {
            //no controlado
            Logger_.error("Error NO CONTROLADO de conexion con la base de datos consultando el pais con id {}", uuid, excepcion);

            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
        }
        return PaisEncontrado;
    }
    //bn
    @Override
    public List<PaisEntidad> consultarPorFiltro(PaisEntidad filtro) {
        var cont=0;
        var paisesEncontrados = new ArrayList<PaisEntidad>();
        var sentenciaSql = "select id, nombre from pais ";
        var parametros = new ArrayList<Object>();
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql ,(cont==0 ? " where " : " and ") ," id = ? ");
            parametros.add(filtro.getId());
            cont=cont +1;
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql ,(cont==0 ? " where " : " and ") ," nombre like ? ");
            parametros.add(UtilTexto.getUtilTexto().concatenar("%",filtro.getNombre(),"%"));
            cont=cont +1;
        }
        sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql , " order by nombre asc ");

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llegó, se arma un país y se agrega a la lista
            while (resultado.next()) {
                var pais = new PaisEntidad.Builder().id(UUID.fromString(resultado.getString("id"))).nombre(resultado.getString("nombre")).build();
                paisesEncontrados.add(pais);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PAIS_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return paisesEncontrados;
    }

    @Override
    public List<PaisEntidad> consultarPorNombre(String nombre) {
        var paisesEncontrados = new ArrayList<PaisEntidad>();
        var sentenciaSql = "select id, nombre from pais where nombre =? ";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, nombre);

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var pais = new PaisEntidad.Builder().id(UUID.fromString(resultado.getString("id"))).nombre(resultado.getString("nombre")).build();
                paisesEncontrados.add(pais);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PAIS_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return paisesEncontrados;
    }

    //bn
     @Override
      public List<PaisEntidad> consultarTodos() {
         var sentenciaSql="select id,nombre from pais order by nombre asc";
         var paisesEncontrados = new ArrayList<PaisEntidad>();

         try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

           var resultado = sentencia.executeQuery();
           while (resultado.next()) {
              var pais = new PaisEntidad.Builder().id(UUID.fromString(resultado.getString("id"))).nombre(resultado.getString("nombre")).build();
             paisesEncontrados.add(pais);
              }
         } catch (SQLException excepcion) {
        //el controlado
             var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PAISES;
             throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
        //no controlado
          var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PAISES;
          throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
      }
    return paisesEncontrados;
   }

//bn
    @Override
    public void crear(PaisEntidad entidad) {
    var sentenciaSql = "insert into pais(id,nombre) values(?,?)";
    //la conexion, con un try , ya que vamos a intentar conectarlo y alli hacer las operaciones
    try(var sentencia= getConexion().prepareStatement(sentenciaSql)){
        //se llenan los datos
        sentencia.setObject(1, entidad.getId());
        sentencia.setString(2, entidad.getNombre());
        //como ya los tengo listos hago el metodo
        sentencia.executeUpdate();
    } catch (SQLException excepcion){
        var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_CREANDO_PAIS;
        throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
        //el controlado
    } catch (Exception excepcion) {
        //no controlado
        var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PAIS;
        throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
    }


   }

    @Override
     public void eliminar(UUID uuid) {
        var sentenciaSql = "delete from pais where id =?";
        // Autocloseable
        try(var sentencia= getConexion().prepareStatement(sentenciaSql)){
            sentencia.setObject(1, uuid);
            sentencia.executeUpdate();
        } catch (SQLException excepcion){
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_ELIMINANDO_PAIS;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
            //el controlado
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PAIS;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
        }

     }
}