package co.edu.uco.libreriauco.libreriauco.controlador;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CsrfControlador {

    @GetMapping("/api/v1/csrf")
    public Map<String, String> obtenerTokenCsrf(CsrfToken token) {
        return Map.of("nombreEncabezado", token.getHeaderName(), "token", token.getToken());
    }
}


