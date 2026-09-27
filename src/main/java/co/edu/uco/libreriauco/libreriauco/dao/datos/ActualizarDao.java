package co.edu.uco.libreriauco.libreriauco.dao.datos;

public interface ActualizarDao<E,Id> {
    void actualizar( Id id, E entidad);
    //tambien va a recibir una entidad
}
