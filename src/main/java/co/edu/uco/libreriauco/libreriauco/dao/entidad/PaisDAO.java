package co.edu.uco.libreriauco.libreriauco.dao.entidad;

import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;

import java.util.UUID;

public interface PaisDAO extends CrearDao<PaisEntidad>, ConsultarDao<PaisEntidad, UUID>, ActualizarDao<PaisEntidad, UUID>, EliminarDao<PaisEntidad,UUID> {
}
