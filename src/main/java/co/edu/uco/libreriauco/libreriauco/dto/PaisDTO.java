package co.edu.uco.libreriauco.libreriauco.dto;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;
import org.springframework.web.bind.annotation.BindParam;

import java.util.UUID;

public class PaisDTO {

    @BindParam("id")
    private UUID id;
    @BindParam("nombre")
    private String nombre;


    public PaisDTO(){
        setId(UtilId.valorDefecto(id));
        setNombre(UtilTexto.vacia);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id =UtilId.valorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        //Hago control , ent no quiero que un nombre de un pasi tenga espacios en blanco al inicio o al final
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }
}
