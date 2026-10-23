package co.edu.uco.libreriauco.libreriauco.controlador.impl;

import co.edu.uco.libreriauco.libreriauco.controlador.AbstractRestController;
import co.edu.uco.libreriauco.libreriauco.controlador.respuesta.RespuestaOk;
import co.edu.uco.libreriauco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.libreriauco.negocio.fachada.PaisFachada;
import co.edu.uco.libreriauco.libreriauco.negocio.fachada.impl.PaisFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisControlador extends AbstractRestController {



   //...../api/v1/paises?id=valor&nombre=Colombia
    @GetMapping
    public  ResponseEntity<RespuestaOk<PaisDTO>> consultarPorFiltro(PaisDTO filtro){

        var fachada = new PaisFachadaImpl();
        var paises = fachada.consultarPorFiltro(filtro);
        RespuestaOk<PaisDTO> respuesta = RespuestaOk.crear(paises);
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<RespuestaOk<PaisDTO>> consultarPorId(@PathVariable UUID id ){
        var fachada = new PaisFachadaImpl();
        var pais = fachada.consultarPorId(id);
        RespuestaOk<PaisDTO> respuesta = RespuestaOk.crear(pais);
        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/dummy")
    public  PaisDTO obtenerDummy(){
        return new PaisDTO();
    }

    @GetMapping("/login")
    public  String hacerLogin(@AuthenticationPrincipal OidcUser datosUsuario){
        return "Hola Usuario %s. Te has autenticado con el correo %s . Su token es %s".formatted(datosUsuario.getFullName(),
                datosUsuario.getEmail(), datosUsuario.getIdToken().getTokenValue());
    }

    @PostMapping
    public ResponseEntity<RespuestaOk<String>> registrarInformacionNuevoPais(@RequestBody PaisDTO datos ){
        var fachada = new PaisFachadaImpl();
        fachada.registrarInformacionNuevoPais(datos);
        RespuestaOk<String> respuesta = RespuestaOk.crear("Se ha registrado la informacion del nuevo pais de manera exitosa");
        return ResponseEntity.ok(respuesta);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<RespuestaOk<String>> modificarInformacionPaisExistente (@PathVariable UUID id, @RequestBody PaisDTO datos){
        var fachada = new PaisFachadaImpl();
        fachada.modificarInformacionPaisExistente(id,datos);
        RespuestaOk<String> respuesta = RespuestaOk.crear("Se ha modificado la informacion del pais deseado de manera exitosa");
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaOk<String>> darBajaInformacionPaisExistente(@PathVariable UUID id){
        var fachada = new PaisFachadaImpl();
        fachada.darBajaInformacionPaisExistente(id);
        RespuestaOk<String> respuesta = RespuestaOk.crear("Se ha eliminado el pais deseado de manera exitosa");
        return ResponseEntity.ok(respuesta);

    }


}
