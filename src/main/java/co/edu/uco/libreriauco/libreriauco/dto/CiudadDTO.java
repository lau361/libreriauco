package co.edu.uco.libreriauco.libreriauco.dto;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilTexto;

import java.util.UUID;

public class CiudadDTO {
    //private PaisDTO pais;
    private DepartamentoDTO departamento;
    private UUID id;
    private String nombre;

    public CiudadDTO(){
        setId(UtilId.valorDefecto(id));
        setNombre(UtilTexto.vacia);
        //setPais(new PaisDTO());
        setDepartamento(new DepartamentoDTO());
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

    public DepartamentoDTO getDepartamento(){
        return departamento;
    }
    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(departamento, new DepartamentoDTO());
    }

}
