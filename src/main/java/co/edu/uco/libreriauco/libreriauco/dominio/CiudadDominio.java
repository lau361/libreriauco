package co.edu.uco.libreriauco.libreriauco.dominio;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilTexto;

import java.util.UUID;

public class CiudadDominio {
    private UUID id;
    private String nombre;
    private DepartamentoDominio departamento;


    //privado para que nadie desde afuera me pueda crear un objeto de este tipo
    // si no que la misma clase pueda controlar como se crean los objetos de este tipo
    //por medio de static
    private CiudadDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.departamento = builder.departamento;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public DepartamentoDominio getDepartamento() {
        return departamento;
    }

    public static class Builder {
        //tiene los mismos atributos que la clase principal
        private UUID id;
        private String nombre;
        private DepartamentoDominio departamento;

        public Builder(){
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            departamento = new DepartamentoDominio.Builder().build();
        }
        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
            return this;
        }
        public  Builder departamento(DepartamentoDominio departamento) {
            this.departamento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(departamento, new DepartamentoDominio.Builder().build());
            return this;
        }

        public CiudadDominio build() {
            return new CiudadDominio(this);
        }
    }

}
