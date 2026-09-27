package co.edu.uco.libreriauco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUcoNegocioExcepcion extends LibreriaUcoExcepcion {

    private static final long serialVersionUID = -65678901234567890L;

    private LibreriaUcoNegocioExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        super(Capa.NEGOCIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
}
