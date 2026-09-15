package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilId;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilTexto;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilObjeto;

import java.util.UUID;

public class CiudadEntidad {
        private DepartamentoEntidad departamento;
        private UUID id;
        private String nombre;

        private CiudadEntidad(UUID id, String nombre ,DepartamentoEntidad departamento) {
            super();
            this.id = id;
            this.nombre = nombre;
            this.departamento = departamento;
        }

        private CiudadEntidad(Builder builder) {
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

        public static class Builder {
            private UUID id;
            private String nombre;
            private DepartamentoEntidad departamento;
            public Builder() {
                id = UtilId.valorDefecto(id);
                nombre = UtilTexto.vacia;
                departamento = new DepartamentoEntidad.Builder().build();
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

            public CiudadEntidad Build() {
                return build();
            }

        }


}

