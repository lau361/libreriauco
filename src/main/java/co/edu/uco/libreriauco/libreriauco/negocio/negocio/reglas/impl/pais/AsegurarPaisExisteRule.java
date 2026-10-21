package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

import java.util.UUID;

public class AsegurarPaisExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarPaisExisteRule();

    private AsegurarPaisExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idPais = (UUID) datos[0];
        var daoFactory = (DaoFactory) datos[1];

        var paisEncontrado = daoFactory.obtenerPaisDAO().consultarPorId(idPais);

        //si no hay un pais con ese id, no se puede modificar ni dar de baja
        if (UtilId.VALOR_DEFECTO.equals(paisEncontrado.getId())) {
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_NO_EXISTE;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
