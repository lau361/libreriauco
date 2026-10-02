package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilTexto;

import java.util.UUID;
public class PaisEntidad {

        private UUID id;
        private String nombre;

       public PaisEntidad(){
           setId(UtilId.valorDefecto());
           setNombre(UtilTexto.vacia);
       }

       public PaisEntidad (UUID id, String nombre) {
            setId(id);
            setNombre(nombre);
        }

        private PaisEntidad(Builder builder) {
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
            private UUID id;
            private String nombre;

            public Builder() {
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

            public PaisEntidad build() {
                return new PaisEntidad(this);
            }

            public PaisEntidad Build() {
                return build();
            }

        }


}

