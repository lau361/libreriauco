package co.edu.uco.libreriauco.libreriauco.negocio.negocio;

import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;

import java.util.List;
import java.util.UUID;

public interface PaisNegocio {
    void registrarInformacionNuevoPais(PaisDominio datos);
    void modificarInformacionPaisExistente(UUID id, PaisDominio paisDominio);
    void darBajaInformacionPaisExistente(UUID id);

    List<PaisDominio> consultarPorFiltro(PaisDominio filtro);
    List<PaisDominio> consultarTodos();
    PaisDominio consultarPorId(UUID id);

}
