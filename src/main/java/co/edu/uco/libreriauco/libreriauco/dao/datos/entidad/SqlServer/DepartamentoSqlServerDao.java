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
        return null;
    }

    @Override
    public List<DepartamentoEntidad> consultarPorFiltro(DepartamentoEntidad filtro) {

        var departamentosEncontrados = new ArrayList<DepartamentoEntidad>();
        var sentenciaSql = "select d.id_departamento ,d.nombre as nombre_departamento, p.id_pais , p.nombre  as nombre_pais from departamento as d inner join pais p on d.id_pais = p.id_pais where 1=1";
        var parametros = new ArrayList<Object>();
        //este seria el de departamento
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and d.id = ?";
            parametros.add(filtro.getId());
        }
        //este el de pais
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getPais().getId())) {
            sentenciaSql = sentenciaSql + " and p.id = ?";
            parametros.add(filtro.getPais().getId());
        }

        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = sentenciaSql + " and d.nombre = ?";
            parametros.add(filtro.getNombre());
        }
        //pais
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getPais().getNombre())) {
            sentenciaSql = sentenciaSql + " and p.nombre = ?";
            parametros.add(filtro.getPais().getNombre());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by d.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llegó, se arma un país y se agrega a la lista
            while (resultado.next()) {
                //primero se arma el pais para luego poderlo asignar a departamento
                //necesito el pais ya listo para poderlo asignar
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
    public List<DepartamentoEntidad> consultarTodos() {
        return null;
    }

}
