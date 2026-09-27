package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.PaisDAO;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class PaisSqlServerDao extends SqlDAO implements PaisDAO {


    public PaisSqlServerDao(Connection conexion) {
        super(conexion);
    }

    @Override
    public void actualizar(UUID uuid, PaisEntidad entidad) {

    }

    //bn
    @Override
    public PaisEntidad consultarPorId(UUID uuid) {
        return null;
    }
    //bn
    @Override
    public List<PaisEntidad> consultarPorFiltro(PaisEntidad filtro) {
        return null;
    }

    //bn
    @Override
    public List<PaisEntidad> consultarTodos() {
        return null;
    }
   //bn
    @Override
    public void crear(PaisEntidad entidad) {
    }

    @Override
    public void eliminar(UUID uuid) {

    }
}
