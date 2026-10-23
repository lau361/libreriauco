package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

import java.util.UUID;

public class AsegurarNombrePaisModificadoNoExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarNombrePaisModificadoNoExisteRule();

    private AsegurarNombrePaisModificadoNoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idPais = (UUID) datos[0];
        var nombrePais = (String) datos[1];
        var daoFactory = (DaoFactory) datos[2];
        var resultados = daoFactory.obtenerPaisDAO().consultarPorNombre(nombrePais);

        for (var paisEncontrado : resultados) {
            if (!paisEncontrado.getId().equals(idPais)) {
                var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_EXISTE_CON_EL_MISMO_NOMBRE_A_MODIFICAR;
                throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
            }
        }
    }
}
