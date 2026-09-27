package co.edu.uco.libreriauco.libreriauco.dao.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.entidad.CiudadDao;
import co.edu.uco.libreriauco.libreriauco.entidad.CiudadEntidad;

import java.util.List;
import java.util.UUID;

public class CiudadSqlServerDao implements CiudadDao {
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
