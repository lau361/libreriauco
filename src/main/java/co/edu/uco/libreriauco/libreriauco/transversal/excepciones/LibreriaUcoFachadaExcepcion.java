package co.edu.uco.libreriauco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUcoFachadaExcepcion extends LibreriaUcoExcepcion {

    private static final long serialVersionUID = -65678901234567891L;

    private LibreriaUcoFachadaExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        super(Capa.FACHADA, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario){
        return new LibreriaUcoFachadaExcepcion(mensajeUsuario,mensajeUsuario,
                new Exception(mensajeUsuario));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico){
        return new LibreriaUcoFachadaExcepcion(mensajeUsuario,mensajeTecnico,
                new Exception(mensajeTecnico));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        return new LibreriaUcoFachadaExcepcion(mensajeUsuario,mensajeTecnico,
                excepcionRaiz);
    }

}
