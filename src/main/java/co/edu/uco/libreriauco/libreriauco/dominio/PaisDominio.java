package co.edu.uco.libreriauco.libreriauco.dominio;

import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilTexto;

import java.util.UUID;

public class PaisDominio {
    private UUID id;
    private String nombre;

    //privado para que nadie desde afuera me pueda crear un objeto de este tipo
    // si no que la misma clase pueda controlar como se crean los objetos de este tipo
    //por medio de static

    private PaisDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public static class Builder {
        //tiene los mismos atributos que la clase principal
        private UUID id;
        private String nombre;

        public Builder () {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
        }

        //metodos para colocar los nombres , los metodos se llaman igula que el atributo

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public PaisDominio build() {
            return new PaisDominio(this);
        }
    }

}
//repasar builder
//vamos a tener un paquete que se va a llamar entidad es aquel que se usa para hacer la relacion correspondencia , mapeo contra
//cada estrcutura de la base de datos , Investigar Patron Object Relational Mapping

//tarea
//crear paquete como dto , pero con nombre entiedad , se va a duplicar las clases y los atributos van a ser iguales