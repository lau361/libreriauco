package co.edu.uco.libreriauco.libreriauco.controlador;

import co.edu.uco.libreriauco.libreriauco.controlador.respuesta.RespuestaError;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoDatosExcepcion;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoExcepcion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AbstractRestController {
    @ExceptionHandler(LibreriaUcoDatosExcepcion.class)
    public ResponseEntity<RespuestaError<String>> controlarLibreriaUcoExcepcion(LibreriaUcoExcepcion exception){
        var respuesta = RespuestaError.crear(exception.get)
    }


@RestControllerAdvice
public class AbstractRestController {
   @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError<String>> noControlado(Exception exception){
       var respuesta = RespuestaError.crear(exception.get)
   }
}
