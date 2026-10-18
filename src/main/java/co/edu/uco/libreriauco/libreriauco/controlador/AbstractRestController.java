package co.edu.uco.libreriauco.libreriauco.controlador;

import co.edu.uco.libreriauco.libreriauco.controlador.respuesta.RespuestaError;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public abstract class AbstractRestController {


    @ExceptionHandler(LibreriaUcoExcepcion.class)
    public ResponseEntity<RespuestaError<String>> controlarLibreriaUcoExcepcion(LibreriaUcoExcepcion exception){
        var respuesta = RespuestaError.crear(exception.getMensajeUsuario());
        //logger.print exception
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError<String>> controlarExcepcion(Exception exception){
        var mensajeRespuesta= CatalogoMensajes.AbstractRestController.USUARIO_ERROR_PROBLEMA_INESPERADO_EN_CONTROLADOR;
        var respuesta = RespuestaError.crear(mensajeRespuesta);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RespuestaError<String>> controlarNoResourceFoundException(NoResourceFoundException exception){
        var mensajeRespuesta= UtilTexto.getUtilTexto().concatenar("Este recurso no existe :  "  ,exception.getResourcePath() , "  por el metodo: ", exception.getHttpMethod() );
        var respuesta = RespuestaError.crear(mensajeRespuesta);
        //esto no puede estar es solo ej
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }



}
