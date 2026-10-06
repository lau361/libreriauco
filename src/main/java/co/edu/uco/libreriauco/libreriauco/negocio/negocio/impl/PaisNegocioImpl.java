package co.edu.uco.libreriauco.libreriauco.negocio.negocio.impl;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler.impl.PaisEntidadAssembler;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

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

        var paisEntidad = PaisEntidadAssembler.getInstance().convertirAEntidad(datos);
        paisEntidad.setId(generarIdPaisUnico());
        daoFactory.obtenerPaisDAO().crear(paisEntidad);
    }

    private void asegurarDatosRegistroNuevoPaisValidos(PaisDominio datos) {
        // Implementar validaciones de datos
    }
    private void asegurarNombreNuevoPaisNoExiste(String nombrePais) {
        var entidadFiltro = new PaisEntidad();
        entidadFiltro.setNombre(nombrePais);

        var resultados = daoFactory.obtenerPaisDAO().consultarPorFiltro(entidadFiltro);
        if (!resultados.isEmpty()) {
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_EXISTE_CON_EL_MISMO_NOMBRE_A_CREAR;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
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
