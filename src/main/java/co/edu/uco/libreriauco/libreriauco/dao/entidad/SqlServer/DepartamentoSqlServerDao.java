package co.edu.uco.libreriauco.libreriauco.dao.entidad.SqlServer;

import co.edu.uco.libreriauco.libreriauco.dao.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.entidad.DepartamentoEntidad;

import java.util.List;
import java.util.UUID;

public class DepartamentoSqlServerDao implements DepartamentoDao{

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
