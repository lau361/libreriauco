package co.edu.uco.libreriauco.libreriauco.dao.datos;

import java.util.List;

public interface ConsultarDao<E,Id> {
     E consultarPorId(Id id);
     List<E>consultarPorFiltro(E filtro);
     List<E> consultarTodos();

}
