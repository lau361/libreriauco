package co.edu.uco.libreriauco.libreriauco.negocio.negocio.impl;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler.impl.PaisEntidadAssembler;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.especificacion.IdPaisExisteEspecificacion;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais.AsegurarNombreNuevoPaisNoExisteRule;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais.ValidarDatosRegistrarInformacionNuevoPaisRule;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;

import java.util.List;
import java.util.UUID;

public class PaisNegocioImpl implements PaisNegocio {

    private DaoFactory daoFactory;

    protected PaisNegocioImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoPais(PaisDominio datos) {
        ValidarDatosRegistrarInformacionNuevoPaisRule.obtenerInstancia().ejecutar(datos);
        AsegurarNombreNuevoPaisNoExisteRule.obtenerInstancia().ejecutar(datos.getNombre(), daoFactory);
        var paisEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(datos);
        paisEntidad.setId(generarIdPaisUnico());
        daoFactory.obtenerPaisDAO().crear(paisEntidad);
    }

    private UUID generarIdPaisUnico() {
        //genero un id al azar
        var id = UtilId.generarId();

        //mientras ese id ya exista, genero otro
        while (new IdPaisExisteEspecificacion(daoFactory).seCumple(id)) {
            id = UtilId.generarId();
        }

        //si llego aqui, el id esta libre
        return id;
    }

    @Override
    public void modificarInformacionPaisExistente(UUID id, PaisDominio paisDominio) {

    }

    @Override
    public void darBajaInformacionPaisExistente(UUID id) {

    }

    @Override
    public List<PaisDominio> consultarPorFiltro(PaisDominio filtro) {
        return List.of();
    }

    @Override
    public List<PaisDominio> consultarTodos() {
        return List.of();
    }

    @Override
    public PaisDominio consultarPorId(UUID id) {
        return null;
    }
}
