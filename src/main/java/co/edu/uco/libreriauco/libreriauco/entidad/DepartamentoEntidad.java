package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.UUID;

public class DepartamentoEntidad {
    private PaisEntidad pais;
    private UUID id;
    private String nombre;

    // si creo un departamento sin darle informacion , ennt el departamento se creara de esta manera
    public DepartamentoEntidad(){
        setId(UtilId.valorDefecto(id));//tampoco le puso el id
        setNombre(UtilTexto.vacia);
        setPais(new PaisEntidad());
    }
    public DepartamentoEntidad(UUID id, String nombre, PaisEntidad pais) {
        setId(UtilId.valorDefecto(id));//tampoco le puso el id
        setNombre(UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre));
        setPais(pais);
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }

    public PaisEntidad getPais() {
        return pais;
    }

    public void setPais(PaisEntidad pais) {
        //si el pais es nulo, se le asigna un nuevo PaisEntidad, de lo contrario se le asigna el pais que se le pasa como parametro
        this.pais = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pais, new PaisEntidad());
    }
}
