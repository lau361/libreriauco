package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.UUID;

public class DepartamentoEntidad {

    private PaisEntidad pais;
    private UUID id;
    private String nombre;

    private DepartamentoEntidad(Builder builder) {
        this.pais = builder.pais;
        this.id = builder.id;
        this.nombre = builder.nombre;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public PaisEntidad getPais() {
        return pais;
    }

    public static class Builder {

        private PaisEntidad pais;
        private UUID id;
        private String nombre;

        // si creo un departamento sin darle informacion , ennt el departamento se creara de esta manera
        public Builder() {
            pais = new PaisEntidad.Builder().build();
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

        public Builder pais(PaisEntidad pais) {
            //si el pais es nulo, se le asigna un nuevo PaisEntidad, de lo contrario se le asigna el pais que se le pasa como parametro
            this.pais = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pais, new PaisEntidad.Builder().build());
            return this;
        }

        public DepartamentoEntidad build() {
            return new DepartamentoEntidad(this);
        }
    }
}
