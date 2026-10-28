package co.edu.uco.libreriauco.libreriauco.negocio.negocio.impl;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler.impl.PaisEntidadAssembler;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais.*;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisNegocioImpl implements PaisNegocio {

    private DaoFactory daoFactory;

    public PaisNegocioImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoPais(PaisDominio datos) {
        ValidarDatosRegistrarInformacionNuevoPaisRule.obtenerInstancia().ejecutar(datos);
        AsegurarNombreNuevoPaisNoExisteRule.obtenerInstancia().ejecutar(datos.getNombre(), daoFactory);
        var idPais = generarIdPaisUnico();
        AsegurarIdPaisNoExisteRule.obtenerInstancia().ejecutar(idPais, daoFactory);

        var paisConId = new PaisDominio.Builder()
                .id(idPais)
                .nombre(datos.getNombre())
                .build();
        var paisEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(paisConId);
        daoFactory.obtenerPaisDAO().crear(paisEntidad);
    }

    private UUID generarIdPaisUnico() {
        return UtilId.generarId();
    }

    @Override
    public void modificarInformacionPaisExistente(UUID id, PaisDominio paisDominio) {
        AsegurarPaisExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
        AsegurarNombrePaisValidoRule.obtenerInstancia().ejecutar(paisDominio.getNombre());
        AsegurarNombrePaisModificadoNoExisteRule.obtenerInstancia().ejecutar(id, paisDominio.getNombre(), daoFactory);
        var paisEntidad = new PaisEntidad.Builder().id(id).nombre(paisDominio.getNombre()).build();
        daoFactory.obtenerPaisDAO().actualizar(id, paisEntidad);
    }

    @Override
    public void darBajaInformacionPaisExistente(UUID id) {
        AsegurarPaisExisteRule.obtenerInstancia().ejecutar(id, daoFactory);
        AsegurarPaisNoTengaDepartamentos.obtenerInstancia().ejecutar(id, daoFactory);
        daoFactory.obtenerPaisDAO().eliminar(id);
    }

    @Override
    public List<PaisDominio> consultarPorFiltro(PaisDominio filtro) {
        var filtroEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(filtro);
        var paisesEncontrados = daoFactory.obtenerPaisDAO().consultarPorFiltro(filtroEntidad);
        var paisesDominio = new ArrayList<PaisDominio>();
        for (var paisEntidad : paisesEncontrados) {
            paisesDominio.add(PaisEntidadAssembler.getInstance().convertirADominio(paisEntidad));
        }
        return paisesDominio;
    }

    @Override
    public List<PaisDominio> consultarTodos() {
        var paisesEncontrados = daoFactory.obtenerPaisDAO().consultarTodos();
        var paisesDominio = new ArrayList<PaisDominio>();
        for (var paisEntidad : paisesEncontrados) {
            paisesDominio.add(PaisEntidadAssembler.getInstance().convertirADominio(paisEntidad));
        }
        return paisesDominio;
    }

    @Override
    public PaisDominio consultarPorId(UUID id) {
        var paisEncontrado = daoFactory.obtenerPaisDAO().consultarPorId(id);
        return PaisEntidadAssembler.getInstance().convertirADominio(paisEncontrado);
    }
}
