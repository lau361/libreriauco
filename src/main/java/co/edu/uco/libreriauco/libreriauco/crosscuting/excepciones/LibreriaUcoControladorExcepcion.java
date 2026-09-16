package co.edu.uco.libreriauco.libreriauco.crosscuting.excepciones;

import co.edu.uco.libreriauco.libreriauco.crosscuting.excepciones.enums.Capa;

public class LibreriaUcoControladorExcepcion extends LibreriaUcoExcepcion {

  private static final long serialVersionUID = -65678901234567890L;


  private LibreriaUcoControladorExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz){
    super(Capa.CONTROLADOR, mensajeUsuario, mensajeTecnico, excepcionRaiz);
  }
  public static LibreriaUcoExcepcion crear(String mensajeUsuario ){
    return new LibreriaUcoControladorExcepcion(mensajeUsuario, mensajeUsuario, new Exception(mensajeUsuario));

  }
  public static LibreriaUcoExcepcion crear(String mensajeTecnico , String mensajeUsuario ){
    return new LibreriaUcoControladorExcepcion(mensajeUsuario, mensajeTecnico, new Exception(mensajeTecnico));

  }
  public static LibreriaUcoExcepcion crear(String mensajeUsuario , String mensajeTecnico ){
    return new LibreriaUcoControladorExcepcion(mensajeUsuario, mensajeTecnico, excepcionRaiz);

  }


}
