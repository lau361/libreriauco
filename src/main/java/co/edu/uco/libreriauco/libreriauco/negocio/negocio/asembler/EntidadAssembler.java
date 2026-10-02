package co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler;

public interface EntidadAssembler<D, E> {
    E convertirAEntidad(D dominio);
    D convertirADominio(E entidad);

}
