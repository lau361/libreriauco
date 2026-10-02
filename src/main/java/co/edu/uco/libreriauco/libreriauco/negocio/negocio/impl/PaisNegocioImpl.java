package co.edu.uco.libreriauco.libreriauco.negocio.negocio.impl;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler.impl.PaisEntidadAssembler;

import java.util.List;
import java.util.UUID;

public class PaisNegocioImpl implements PaisNegocio {


    private DaoFactory daoFactory;

    protected PaisNegocioImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoPais(PaisDominio datos) {
        asegurarDatosRegistroNuevoPaisValidos( datos);
        asegurarNombreNuevoPaisNoExiste(datos.getNombre());

        var paisEntidad = PaisEntidadAssembler.getInstance().convertirDominioAEntidad(datos);
        paisEntidad.setId(generarIdPaisUnico());

        daoFactory.obtenerPaisDAO().crear(paisEntidad);
    }

    private void asegurarDatosRegistroNuevoPaisValidos(PaisDominio datos) {
        var entidadFiltro = new PaisEntidad();
        entidadFiltro.setNombre(nombrePais);
        // Implementar validaciones de datos
    }
    private void asegurarNombreNuevoPaisNoExiste(String nombrePais) {
        // Implementar verificación de existencia
    }
    private UUID generarIdPaisUnico() {
        // Implementar generación de ID único
        return UUID.randomUUID();
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
