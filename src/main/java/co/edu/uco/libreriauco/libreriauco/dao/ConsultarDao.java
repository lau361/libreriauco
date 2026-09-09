package co.edu.uco.libreriauco.libreriauco.dao;

import java.util.List;

public interface ConsultarDao<E,Id> {
     // como voy a consultar por id , el me va a devolver un objeto de tipo entidad
     E consultarPorId(Id id);
     List<E>consultarPorFiltro(E filtro);
     List<E> consultarTodos();

}
