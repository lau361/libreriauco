package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

public class ValidarDatosRegistrarInformacionNuevoPaisRule implements Rule<PaisDominio> {

    private static final Rule<PaisDominio> instancia = new ValidarDatosRegistrarInformacionNuevoPaisRule();

    private ValidarDatosRegistrarInformacionNuevoPaisRule() {
    }

    public static final Rule<PaisDominio> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(PaisDominio... datos) {
        var dominio = datos[0];
        if(UtilObjeto.esNulo(dominio)){
            var mensaje = CatalogoMensajes.PaisNegocioImpl.DATOS_PAIS_NULLS;
            throw LibreriaUcoNegocioExcepcion.crear(mensaje);
        }else{
            AsegurarNombrePaisValidoRule.obtenerInstancia().ejecutar(dominio.getNombre());

        }
    }
}
