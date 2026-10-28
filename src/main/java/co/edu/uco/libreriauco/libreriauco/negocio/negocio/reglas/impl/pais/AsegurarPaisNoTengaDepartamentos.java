package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

import java.util.UUID;

public class AsegurarPaisNoTengaDepartamentos implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarPaisNoTengaDepartamentos();

    private AsegurarPaisNoTengaDepartamentos() {

    }
    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idPais = (UUID) datos[0];
        var daoFactory = (DaoFactory) datos[1];
        var resultados = daoFactory.obtenerDepartamentoDAO().consultarDepartamentosAsociados(idPais);

        if ( !resultados.isEmpty()) {
                var mensajeUsuario = "El pais tiene departamentos asociados";
                throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
