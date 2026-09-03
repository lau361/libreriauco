package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilObjeto;
import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilTexto;


import java.util.UUID;

public class DepartamentoEntidad {
        private PaisEntidad pais;
        private UUID id;
        private String nombre;

        private DepartamentoEntidad(UUID id, String nombre) {
            super();
            this.id = id;
            this.nombre = nombre;
            this.pais = new PaisEntidad.Builder().Build();
        }


        private DepartamentoEntidad(Builder builder) {
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
            private PaisEntidad pais;

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

            public DepartamentoEntidad Build() {
                return new DepartamentoEntidad(this);
            }

            public PaisEntidad getPais() {
                return pais;
            }
            public void setPais(PaisEntidad pais) {
                this.pais = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pais, new PaisEntidad.Builder().Build());
            }
        }


}

