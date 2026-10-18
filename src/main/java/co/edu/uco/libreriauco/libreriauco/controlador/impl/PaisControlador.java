package co.edu.uco.libreriauco.libreriauco.controlador.impl;

import co.edu.uco.libreriauco.libreriauco.controlador.AbstractRestController;
import co.edu.uco.libreriauco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.libreriauco.negocio.fachada.impl.PaisFachadaImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisControlador extends AbstractRestController {

    @GetMapping
    public  String consultarTodos(){
        return "Solicitud  exitosa";
    }

    @PostMapping
    public  String registrarInformacionNuevoPais(@RequestBody PaisDTO datos ){
        var fachada = new PaisFachadaImpl();
        fachada.registrarInformacionNuevoPais(datos);
     return "Pais registrado exitosamente";
    }
    @PutMapping("/{id}")
    public  String modificarInformacionPaisExistente (@PathVariable UUID id, @RequestBody PaisDTO datos){
        var fachada = new PaisFachadaImpl();
        fachada.modificarInformacionPaisExistente(id,datos);
        return "Pais modificado exitosamente";
    }
    @DeleteMapping("/{id}")
    public  String darBajaInformacionPaisExistente(@PathVariable UUID id){
        var fachada = new PaisFachadaImpl();
        fachada.darBajaInformacionPaisExistente(id);
        return "Pais eliminado exitosamente";
    }


}
