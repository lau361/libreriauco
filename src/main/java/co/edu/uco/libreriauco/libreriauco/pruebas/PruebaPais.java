package co.edu.uco.libreriauco.libreriauco.pruebas;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.impl.SqlServerDAOFactory;

import java.util.UUID;

public class PruebaPais {
    public static void main(String[] args) {
        // 1. La factory abre la conexión a SQL Server
        var factory = new SqlServerDAOFactory();

        // 2. Le pedimos el DAO de país (ya viene con la conexión)
        var paisDao = factory.obtenerPaisDAO();

        // 3. Consultamos un id que no existe: debe salir INFO y WARN
        paisDao.consultarPorId(UUID.randomUUID());

        // 4. Cerramos la conexión
        factory.cerrarConexion();
    }
}
