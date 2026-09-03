package co.edu.uco.libreriauco.libreriauco.entidad;

import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilId;

import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilTexto;
import co.edu.uco.libreriauco.libreriauco.Utilitario.UtilObjeto;

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
            this.departamento = new DepartamentoEntidad.Builder().Build();
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
                departamento = new DepartamentoEntidad.Builder().Build();
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
                this.departamento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(departamento, new DepartamentoEntidad.Builder().Build());
                return this;
            }

            public CiudadEntidad Build() {
                return new CiudadEntidad(this);
            }

        }


}

