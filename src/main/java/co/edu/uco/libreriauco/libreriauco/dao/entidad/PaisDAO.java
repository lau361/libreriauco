package co.edu.uco.libreriauco.libreriauco.dao.entidad;

import co.edu.uco.libreriauco.libreriauco.dao.ActualizarDao;
import co.edu.uco.libreriauco.libreriauco.dao.ConsultarDao;
import co.edu.uco.libreriauco.libreriauco.dao.CrearDao;
import co.edu.uco.libreriauco.libreriauco.dao.EliminarDao;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;

import java.util.UUID;

public interface PaisDAO extends CrearDao<PaisEntidad>, ConsultarDao<PaisEntidad, UUID>, ActualizarDao<PaisEntidad, UUID>, EliminarDao<UUID> {

}
