package co.edu.uco.libreriauco.libreriauco.negocio.fachada;

import co.edu.uco.libreriauco.libreriauco.dto.PaisDTO;

import java.util.List;
import java.util.UUID;

public interface PaisFachada {
    void registrarInformacionNuevoPais(PaisDTO datos);
    void modificarInformacionPaisExistente(UUID id, PaisDTO datos);
    void darBajaInformacionPaisExistente(UUID id);

    List<PaisDTO> consultarPorFiltro(PaisDTO filtro);
    List<PaisDTO> consultarTodos();
    PaisDTO consultarPorId(UUID id);

}
