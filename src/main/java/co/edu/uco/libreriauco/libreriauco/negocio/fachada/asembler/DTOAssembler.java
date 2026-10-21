package co.edu.uco.libreriauco.libreriauco.negocio.fachada.asembler;

import java.util.List;

public interface DTOAssembler<D, E> {
    E convertirADTO(D dominio);
    D convertirADominio(E dto);
    List<E> convertirADTO(List<D> listaDominios);

}
