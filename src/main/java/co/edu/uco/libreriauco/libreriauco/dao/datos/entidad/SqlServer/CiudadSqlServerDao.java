package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.libreriauco.entidad.CiudadEntidad;
import co.edu.uco.libreriauco.libreriauco.entidad.DepartamentoEntidad;
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

public class CiudadSqlServerDao extends SqlDAO implements CiudadDao {

    public CiudadSqlServerDao(Connection conexion) {
        super(conexion);
    }

    @Override
    public CiudadEntidad consultarPorId(UUID uuid) {
        var sentenciaSql = "select c.id as id_ciudad, c.nombre as nombre_ciudad, "
                + "d.id as id_departamento, d.nombre as nombre_departamento, "
                + "p.id as id_pais, p.nombre as nombre_pais "
                + "from ciudad as c "
                + "inner join departamento as d on c.id_departamento = d.id "
                + "inner join pais as p on d.id_pais = p.id "
                + "where c.id = ?";
        var ciudadEncontrada = new CiudadEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, uuid);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();
                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();
                ciudadEncontrada = new CiudadEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_ciudad")))
                        .nombre(resultado.getString("nombre_ciudad"))
                        .departamento(departamento)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CiudadSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CIUDAD_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CiudadSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CIUDAD_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return ciudadEncontrada;
    }

    @Override
    public List<CiudadEntidad> consultarPorFiltro(CiudadEntidad filtro) {
        var ciudadesEncontradas = new ArrayList<CiudadEntidad>();
        var sentenciaSql = "select c.id as id_ciudad, c.nombre as nombre_ciudad, "
                + "d.id as id_departamento, d.nombre as nombre_departamento, "
                + "p.id as id_pais, p.nombre as nombre_pais "
                + "from ciudad as c "
                + "inner join departamento as d on c.id_departamento = d.id "
                + "inner join pais as p on d.id_pais = p.id "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and c.id = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = sentenciaSql + " and c.nombre = ?";
            parametros.add(filtro.getNombre());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getDepartamento().getId())) {
            sentenciaSql = sentenciaSql + " and d.id = ?";
            parametros.add(filtro.getDepartamento().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getDepartamento().getNombre())) {
            sentenciaSql = sentenciaSql + " and d.nombre = ?";
            parametros.add(filtro.getDepartamento().getNombre());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by c.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman el pais y el departamento para luego asignarlos a la ciudad
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();
                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();
                var ciudad = new CiudadEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_ciudad")))
                        .nombre(resultado.getString("nombre_ciudad"))
                        .departamento(departamento)
                        .build();
                ciudadesEncontradas.add(ciudad);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CiudadSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CIUDAD_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CiudadSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CIUDAD_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return ciudadesEncontradas;
    }

    @Override
    public List<CiudadEntidad> consultarTodos() {
        var sentenciaSql = "select c.id as id_ciudad, c.nombre as nombre_ciudad, "
                + "d.id as id_departamento, d.nombre as nombre_departamento, "
                + "p.id as id_pais, p.nombre as nombre_pais "
                + "from ciudad as c "
                + "inner join departamento as d on c.id_departamento = d.id "
                + "inner join pais as p on d.id_pais = p.id "
                + "order by c.nombre asc";
        var ciudadesEncontradas = new ArrayList<CiudadEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman el pais y el departamento para luego asignarlos a la ciudad
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();
                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();
                var ciudad = new CiudadEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_ciudad")))
                        .nombre(resultado.getString("nombre_ciudad"))
                        .departamento(departamento)
                        .build();
                ciudadesEncontradas.add(ciudad);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CiudadSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_CIUDADES;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CiudadSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_CIUDADES;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return ciudadesEncontradas;
    }
}
