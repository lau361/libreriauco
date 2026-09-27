package co.edu.uco.libreriauco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUcoEntidadExcepcion extends LibreriaUcoExcepcion {

    private static final long serialVersionUID = -65678901234567890L;

    private LibreriaUcoEntidadExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        super(Capa.ENTIDAD, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario){
        return new LibreriaUcoEntidadExcepcion(mensajeUsuario,mensajeUsuario,
                new Exception(mensajeUsuario));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico){
        return new LibreriaUcoEntidadExcepcion(mensajeUsuario,mensajeTecnico,
                new Exception(mensajeTecnico));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        return new LibreriaUcoEntidadExcepcion(mensajeUsuario,mensajeTecnico,
                excepcionRaiz);
    }

}
