package co.edu.uco.libreriauco.libreriauco.controlador.respuesta;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.List;

public class RespuestaOk <D> extends Respuesta<D>{


    protected RespuestaOk(List<D> datos, List<String> mensajes) {
        super(datos, mensajes, EstadoRespuesta.OK);
    }
    public static <D> RespuestaOk<D> crear(List<String> mensajes, List<D> datos){
        return new RespuestaOk<>(datos, mensajes);
    }
    public static <D> RespuestaOk<D> crear(String mensaje){
        var mensajeFormateado = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(mensaje);
        return new RespuestaOk<>(List.of(), List.of(mensajeFormateado));
    }

    //solo le mandaron con los datos
    public static <D> RespuestaOk<D> crear( List<D> datos){
        return new RespuestaOk<>(datos, List.of());
    }
    //solo me mandan una antidad , un objeto de tipo D
    public static <D> RespuestaOk<D> crear( D dato){
        return new RespuestaOk<>(List.of(dato),List.of());
    }
    // o una respuesta vacia
    public static <D> RespuestaOk<D> crear(){
        return new RespuestaOk<>(List.of(),List.of());
    }


}
