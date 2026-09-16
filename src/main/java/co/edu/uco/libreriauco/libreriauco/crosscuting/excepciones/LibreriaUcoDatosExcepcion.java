package co.edu.uco.libreriauco.libreriauco.crosscuting.excepciones;

import co.edu.uco.libreriauco.libreriauco.crosscuting.excepciones.enums.Capa;

public class LibreriaUcoDatosExcepcion extends LibreriaUcoExcepcion {


    private static final long serialVersionUID = -65678901234567890L;


    private LibreriaUcoDatosExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        super(Capa.DATOS, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

}
