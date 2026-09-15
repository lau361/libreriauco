package co.edu.uco.libreriauco.libreriauco.dominio;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilTexto;

import java.util.UUID;

public class DepartamentoDominio {
    private UUID id;
    private String nombre;
    private PaisDominio pais;


    //privado para que nadie desde afuera me pueda crear un objeto de este tipo
    // si no que la misma clase pueda controlar como se crean los objetos de este tipo
    //por medio de static
    private DepartamentoDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.pais = builder.pais;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public PaisDominio getPais() {
        return pais;
    }

    public static class Builder {
        //tiene los mismos atributos que la clase principal
        private UUID id;
        private String nombre;
        private PaisDominio pais;

        public Builder(){
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            pais = new PaisDominio.Builder().build();
        }
        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
            return this;
        }
        public Builder pais(PaisDominio pais) {
            this.pais = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pais, new PaisDominio.Builder().build());
            return this;
        }

        public DepartamentoDominio build() {
            return new DepartamentoDominio(this);
        }
    }

}
