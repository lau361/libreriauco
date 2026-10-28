package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
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

public class DepartamentoSqlServerDao extends SqlDAO implements DepartamentoDao{

    public DepartamentoSqlServerDao(Connection conexion) {
        super(conexion);
    }

    @Override
    public DepartamentoEntidad consultarPorId(UUID uuid) {
        var sentenciaSql =
                """
                select d.id as id_departamento, d.nombre as nombre_departamento,
                p.id as id_pais, p.nombre as nombre_pais
                from departamento as d
                inner join pais as p on d.id_pais = p.id
                where d.id = ?
                """;
        var departamentoEncontrado = new DepartamentoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, uuid);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();
                departamentoEncontrado = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DEPARTAMENTO_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DEPARTAMENTO_POR_ID;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return departamentoEncontrado;
    }

    @Override
    public List<DepartamentoEntidad> consultarPorFiltro(DepartamentoEntidad filtro) {
        var cont= 0;
        var departamentosEncontrados = new ArrayList<DepartamentoEntidad>();
        var sentenciaSql =
                """
                select d.id as id_departamento, d.nombre as nombre_departamento,
                p.id as id_pais, p.nombre as nombre_pais
                from departamento as d
                inner join pais as p on d.id_pais = p.id
                """;
        var parametros = new ArrayList<Object>();

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql ,(cont==0 ? " where " : " and "),"  d.id = ? ");
            parametros.add(filtro.getId());
            cont=cont +1;
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getPais().getId())) {
            sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql ,(cont==0 ? " where " : " and "),"   p.id = ? ");
            parametros.add(filtro.getPais().getId());
            cont=cont +1;
        }

        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql ,(cont==0 ? " where " : " and "),"   d.nombre like ? ");
            parametros.add(UtilTexto.getUtilTexto().concatenar("%",filtro.getNombre(),"%"));
            cont=cont +1;
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getPais().getNombre())) {
            sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql ,(cont==0 ? " where " : " and "),"   p.nombre like ? ");
            parametros.add(UtilTexto.getUtilTexto().concatenar("%",filtro.getPais().getNombre(),"%"));
            cont=cont +1;

        }
        sentenciaSql = UtilTexto.getUtilTexto().concatenar(sentenciaSql , " order by d.nombre asc " );

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();

                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();

                departamentosEncontrados.add(departamento);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DEPARTAMENTO_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DEPARTAMENTO_POR_FILTRO;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return departamentosEncontrados;
    }



    @Override
    public List<DepartamentoEntidad> consultarPorNombre(String nombre) {
        var departamentosEncontrados = new ArrayList<DepartamentoEntidad>();
        var sentenciaSql ="""
                select d.id as id_departamento, d.nombre as nombre_departamento,
                p.id as id_pais, p.nombre as nombre_pais
                from departamento as d
                inner join pais as p on d.id_pais = p.id
                where d.nombre= ?
                """;

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, nombre);

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();

                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();

                departamentosEncontrados.add(departamento);

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
        return departamentosEncontrados;
    }

    @Override
    public List<DepartamentoEntidad> consultarTodos() {
        var sentenciaSql =
                """
                select d.id as id_departamento, d.nombre as nombre_departamento,
                p.id as id_pais, p.nombre as nombre_pais
                from departamento as d
                inner join pais as p on d.id_pais = p.id
                order by d.nombre asc
                """;
        var departamentosEncontrados = new ArrayList<DepartamentoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arma el pais para luego poderlo asignar a departamento
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();
                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();
                departamentosEncontrados.add(departamento);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDao.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DEPARTAMENTOS;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DepartamentoSqlServerDao.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DEPARTAMENTOS;
            throw LibreriaUcoDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return departamentosEncontrados;
    }

    @Override
    public List<DepartamentoEntidad> consultarDepartamentosAsociados(UUID id) {
        var sentenciaSql=
                """
                select p.id as id_pais, p.nombre as nombre_pais,
                 d.nombre as nombre_departamento , d.id as id_departamento
                 from departamento as d
                 inner join pais as p
                 on d.id_pais = p.id
                 where p.id=?
        """;

        var departamentosEncontrados= new  ArrayList<DepartamentoEntidad>();
        try(var sentencia= getConexion().prepareStatement(sentenciaSql)){
            sentencia.setObject(1,id);
            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var pais = new PaisEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_pais")))
                        .nombre(resultado.getString("nombre_pais"))
                        .build();

                var departamento = new DepartamentoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_departamento")))
                        .nombre(resultado.getString("nombre_departamento"))
                        .pais(pais)
                        .build();

                departamentosEncontrados.add(departamento);
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
        return departamentosEncontrados;
    }

}
