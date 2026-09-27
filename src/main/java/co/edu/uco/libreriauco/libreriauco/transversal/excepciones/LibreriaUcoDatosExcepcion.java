package co.edu.uco.libreriauco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUcoDatosExcepcion extends LibreriaUcoExcepcion {
    private static final long serialVersionUID = -65678901234567890L;
    private LibreriaUcoDatosExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.DATOS, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static LibreriaUcoExcepcion crear(String mensajeUsuario){
        return new LibreriaUcoDatosExcepcion(mensajeUsuario,mensajeUsuario,
                new Exception(mensajeUsuario));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico){
        return new LibreriaUcoDatosExcepcion(mensajeUsuario,mensajeTecnico,
                new Exception(mensajeTecnico));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        return new LibreriaUcoDatosExcepcion(mensajeUsuario,mensajeTecnico,
                excepcionRaiz);
    }

}
