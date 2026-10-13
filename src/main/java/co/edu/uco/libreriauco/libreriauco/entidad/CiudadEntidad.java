package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.UUID;

public class CiudadEntidad {

    private DepartamentoEntidad departamento;
    private UUID id;
    private String nombre;

    private CiudadEntidad(Builder builder) {
        this.departamento = builder.departamento;
        this.id = builder.id;
        this.nombre = builder.nombre;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoEntidad getDepartamento() {
        return departamento;
    }

    public static class Builder {

        private DepartamentoEntidad departamento;
        private UUID id;
        private String nombre;

        public Builder() {
            departamento = new DepartamentoEntidad.Builder().build();
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder departamento(DepartamentoEntidad departamento) {
            this.departamento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(departamento, new DepartamentoEntidad.Builder().build());
            return this;
        }

        public CiudadEntidad build() {
            return new CiudadEntidad(this);
        }
    }
}
