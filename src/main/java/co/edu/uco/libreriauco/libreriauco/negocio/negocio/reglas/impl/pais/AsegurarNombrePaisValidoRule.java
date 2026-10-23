package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.impl.pais;

import co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas.Rule;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoNegocioExcepcion;

import static co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto.SOLO_LETRAS_Y_ESPACIOS;

public class AsegurarNombrePaisValidoRule implements Rule<String> {
    private static final Rule<String> instancia = new AsegurarNombrePaisValidoRule();

    private AsegurarNombrePaisValidoRule() {
    }

    public static final Rule<String> obtenerInstancia() {
        return instancia;
    }


    @Override
    public void ejecutar(String... datos) {
        var nombrePais = datos[0];
        validarObligatoriedad(nombrePais);
        validarLongitud(nombrePais, 1, 50);
        validarFormato(nombrePais);
    }
    private void validarObligatoriedad(String dato){
        if(UtilTexto.getUtilTexto().esVacia(dato)){
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.NOMBRE_PAIS_OBLIGATORIO;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarFormato(String dato){
        if(!UtilTexto.getUtilTexto().formatoEsValido(dato,SOLO_LETRAS_Y_ESPACIOS)){
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.FORMATO_NOMBRE_PAIS_NO_VALIDO;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarLongitud(String dato , int longitudMinima, int longitudMaxima){
        if(!UtilTexto.getUtilTexto().longitudCadenaEsValida(dato,longitudMinima,longitudMaxima, true)){
            var mensajeUsuario = CatalogoMensajes.PaisNegocioImpl.LONGITUD_NOMBRE_PAIS_INVALIDA;
            throw LibreriaUcoNegocioExcepcion.crear(mensajeUsuario);
        }
    }

}

