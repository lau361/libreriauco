package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.UUID;

public class PaisEntidad {

    private UUID id;
    private String nombre;

    public PaisEntidad(){
        setId(UtilId.valorDefecto(id));
        setNombre(UtilTexto.vacia);
    }
    public PaisEntidad(UUID id, String nombre){
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
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }
}
