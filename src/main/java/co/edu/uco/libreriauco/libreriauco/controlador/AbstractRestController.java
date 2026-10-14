package co.edu.uco.libreriauco.libreriauco.controlador;

import co.edu.uco.libreriauco.libreriauco.controlador.respuesta.RespuestaError;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public abstract class AbstractRestController {


    @ExceptionHandler(LibreriaUcoDatosExcepcion.class)
    public ResponseEntity<RespuestaError<String>> controlarLibreriaUcoExcepcion(LibreriaUcoExcepcion exception){
        var respuesta = RespuestaError.crear(exception.getMensajeUsuario());
        //logger.print exception
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError<String>> controlarExcepcion(Exception exception){
        var mensajeRespuesta= CatalogoMensajes.AbstractRestController.USUARIO_ERROR_PROBLEMA_INESPERADO_EN_CONTROLADOR;
        var respuesta = RespuestaError.crear(mensajeRespuesta);
        //logger.print exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }



}
