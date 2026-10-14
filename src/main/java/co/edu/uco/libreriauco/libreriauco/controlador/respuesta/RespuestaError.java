package co.edu.uco.libreriauco.libreriauco.controlador.respuesta;

import java.util.List;

public class RespuestaError<D> extends Respuesta<D> {

    protected RespuestaError(List<D> datos, List<String> mensajes) {
        super(datos, mensajes, EstadoRespuesta.ERROR);
    }
    public static RespuestaError<String> crear(final String message){
        return new RespuestaError<>(List.of(), List.of(message));
    }
    public static <D> RespuestaError<D> crear(List<String> mensajes, List<D> datos){
        return new RespuestaError<>(datos, mensajes);
    }

}
