package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

import java.util.UUID;

public class AsegurarIdPaisNoExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarIdPaisNoExisteRule();

    private AsegurarIdPaisNoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idPais = (UUID) datos[0];
        var daoFactory = (DaoFactory) datos[1];

        var paisEncontrado = daoFactory.obtenerPaisDAO().consultarPorId(idPais);

        if (!UtilId.VALOR_DEFECTO.equals(paisEncontrado.getId())) {
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.ID_PAIS_YA_EXISTE;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
