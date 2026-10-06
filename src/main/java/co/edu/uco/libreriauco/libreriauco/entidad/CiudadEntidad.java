package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.dto.DepartamentoDTO;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.UUID;

public class CiudadEntidad {
    //private PaisDTO pais;
    private DepartamentoEntidad departamento;
    private UUID id;
    private String nombre;

    public CiudadEntidad(){
        setId(UtilId.valorDefecto(id));
        setNombre(UtilTexto.vacia);
        setDepartamento(new DepartamentoEntidad());
    }
    public CiudadEntidad(UUID id, String nombre, DepartamentoEntidad departamento) {
        setId(UtilId.valorDefecto(id));
        setNombre(UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre));
        setDepartamento(departamento);
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

    public DepartamentoEntidad getDepartamento(){
        return departamento;
    }
    public void setDepartamento(DepartamentoEntidad departamento) {
        this.departamento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(departamento, new DepartamentoEntidad());
    }

}
