package co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.datos.entidad.SqlDAO;
import co.edu.uco.libreriauco.libreriauco.entidad.DepartamentoEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class DepartamentoSqlServerDao extends SqlDAO implements DepartamentoDao{

    public DepartamentoSqlServerDao(Connection conexion) {
        super(conexion);
    }

    @Override
    public DepartamentoEntidad consultarPorId(UUID uuid) {
        return null;
    }

    @Override
    public List<DepartamentoEntidad> consultarPorFiltro(DepartamentoEntidad filtro) {
        return null;
    }

    @Override
    public List<DepartamentoEntidad> consultarTodos() {
        return null;
    }

}
