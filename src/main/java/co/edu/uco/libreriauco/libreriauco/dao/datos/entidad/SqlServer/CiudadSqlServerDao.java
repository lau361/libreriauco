package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.libreriauco.entidad.CiudadEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class CiudadSqlServerDao extends SqlDAO implements CiudadDao {

    public CiudadSqlServerDao(Connection conexion) {
        super(conexion);
    }

    @Override
    public CiudadEntidad consultarPorId(UUID uuid) {
        return null;
    }

    @Override
    public List<CiudadEntidad> consultarPorFiltro(CiudadEntidad filtro) {
        return List.of();
    }

    @Override
    public List<CiudadEntidad> consultarTodos() {
        return List.of();
    }
}
