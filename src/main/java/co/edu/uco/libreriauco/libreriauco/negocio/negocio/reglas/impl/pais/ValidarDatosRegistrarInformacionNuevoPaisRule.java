package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;

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
        AsegurarNombrePaisValidoRule.obtenerInstancia().ejecutar(dominio.getNombre());
    }
}
