package co.edu.uco.libreriauco.libreriauco.transversal.excepciones;

import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.enums.Capa;

public class LibreriaUcoDtoExcepcion extends LibreriaUcoExcepcion {

    private static final long serialVersionUID = -65678901234567890L;

    private LibreriaUcoDtoExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        super(Capa.DTO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario){
        return new LibreriaUcoDtoExcepcion(mensajeUsuario,mensajeUsuario,
                new Exception(mensajeUsuario));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico){
        return new LibreriaUcoDtoExcepcion(mensajeUsuario,mensajeTecnico,
                new Exception(mensajeTecnico));
    }
    public static LibreriaUcoExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
        return new LibreriaUcoDtoExcepcion(mensajeUsuario,mensajeTecnico,
                excepcionRaiz);
    }

}
