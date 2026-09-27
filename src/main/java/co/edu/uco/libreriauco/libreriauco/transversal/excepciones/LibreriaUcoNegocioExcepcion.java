package co.edu.uco.libreriauco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUcoNegocioExcepcion extends LibreriaUcoExcepcion {

    private static final long serialVersionUID = -65678901234567890L;

    private LibreriaUcoNegocioExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        super(Capa.NEGOCIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario){
        return new LibreriaUcoNegocioExcepcion(mensajeUsuario,mensajeUsuario,
                new Exception(mensajeUsuario));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico){
        return new LibreriaUcoNegocioExcepcion(mensajeUsuario,mensajeTecnico,
                new Exception(mensajeTecnico));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        return new LibreriaUcoNegocioExcepcion(mensajeUsuario,mensajeTecnico,
                excepcionRaiz);
    }

}
