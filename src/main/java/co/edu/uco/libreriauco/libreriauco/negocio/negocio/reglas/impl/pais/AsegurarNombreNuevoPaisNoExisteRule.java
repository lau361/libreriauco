package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

public class AsegurarNombreNuevoPaisNoExisteRule implements Rule<Object> {

    //la politica necesita el factory para poder consultar en la base de datos

    //es de tipo rule object que es lo que esta implementando
    private static final Rule<Object> instancia = new AsegurarNombreNuevoPaisNoExisteRule();

    private AsegurarNombreNuevoPaisNoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var nombrePais = (String)datos[0]; // aseguro que el tipo de dato que reciba sea string
        var daoFactory = (DaoFactory)datos[1]; // lo que viene aqui estoy seguro que es de tipo dao factory
        var resultados = daoFactory.obtenerPaisDAO().consultarPorNombre(nombrePais);
        if (!resultados.isEmpty()) {
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.PAIS_EXISTE_CON_EL_MISMO_NOMBRE_A_CREAR;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
