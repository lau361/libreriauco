package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisSqlServerDao extends SqlDAO implements PaisDAO {


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
        //si el pais no tiene nada para evitar nulo , utilizamos lo de los utilitarios , pero con builder
        var PaisEncontrado= new PaisEntidad.Builder().build();
        try(var sentencia= getConexion().prepareStatement(sentenciaSql)){
            //solo tengo que consultar por id
            sentencia.setObject(1,uuid);

            var resultado = sentencia.executeQuery();
            if(resultado.next()){
                PaisEncontrado = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id")))
                        .nombre(resultado.getString("nombre"))
                        .build();
            }
        }catch (SQLException excepcion){
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PAIS_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
            //el controlado
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.PaisSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PAIS_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario,excepcion.getMessage(),excepcion);
        }
        return PaisEncontrado;
    }
    //bn
    @Override
    public List<PaisEntidad> consultarPorFiltro(PaisEntidad filtro) {
        var sentenciaSql="select id,nombre from pais where nombre=?";
        var paisesEncontrados = new ArrayList<PaisEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //es lo que me van a ingresar
            sentencia.setString(1, filtro.getNombre());

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id")))
                        .nombre(resultado.getString("nombre"))
                        .build();
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
         var sentenciaSql="select id,nombre from pais";
         var paisesEncontrados = new ArrayList<PaisEntidad>();

         try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

           var resultado = sentencia.executeQuery();
           while (resultado.next()) {
              var pais = new PaisEntidad.Builder()
                    .id(UUID.fromString(resultado.getString("id")))
                    .nombre(resultado.getString("nombre"))
                    .build();
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