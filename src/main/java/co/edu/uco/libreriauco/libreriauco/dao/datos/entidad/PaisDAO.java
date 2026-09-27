package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad;

import co.edu.uco.libreriauco.libreriauco.dao.datos.ActualizarDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.ConsultarDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.CrearDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.EliminarDao;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;

import java.util.UUID;

public interface PaisDAO extends CrearDao<PaisEntidad>, ConsultarDao<PaisEntidad, UUID>, ActualizarDao<PaisEntidad, UUID>, EliminarDao<UUID> {

}
