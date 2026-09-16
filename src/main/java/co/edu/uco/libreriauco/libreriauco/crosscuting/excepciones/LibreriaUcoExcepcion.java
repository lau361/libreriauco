package co.edu.uco.libreriauco.libreriauco.crosscuting.excepciones;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilTexto;
import co.edu.uco.libreriauco.libreriauco.crosscuting.excepciones.enums.Capa;

public class LibreriaUcoExcepcion extends RuntimeException{
    private static final long serialVersionUID = -3074234567890123456L;
    private Capa capa;
    private String mensajeUsuario;
    private String mensajeTecnico;
    private Exception excepcionRaiz;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    protected LibreriaUcoExcepcion(Capa capa, String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super();
        setCapa(capa);
        setMensajeUsuario(mensajeUsuario);
        setMensajeTecnico(mensajeTecnico);
        setExcepcionRaiz(excepcionRaiz);

    }

    public Capa getCapa() {
        return capa;
    }

    private void setCapa(Capa capa) {
        this.capa = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(capa, Capa.General);
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    private void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = UtilTexto.getUtilTexto().obtenerValorDefecto(mensajeUsuario);
    }

    public String getMensajeTecnico() {
        return mensajeTecnico;
    }

    private void setMensajeTecnico(String mensajeTecnico) {
        this.mensajeTecnico = UtilTexto.getUtilTexto().obtenerValorDefecto(mensajeTecnico, getMensajeUsuario());
    }

    public Exception getExcepcionRaiz() {
        return excepcionRaiz;
    }

    private void setExcepcionRaiz(Exception excepcionRaiz) {
        this.excepcionRaiz = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(excepcionRaiz, new Exception(getMensajeTecnico()));
    }

}


