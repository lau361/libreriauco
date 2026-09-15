package co.edu.uco.libreriauco.libreriauco.pruebas;

import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilId;

public class PruebaBuilder {
    public static void main(String[] args) {
        PaisDominio dominioPaisUno = new PaisDominio.Builder().build();
        PaisDominio dominioPaisConId= new PaisDominio.Builder().id(UtilId.generarId()).build();
        PaisDominio dominioPaisConNombre= new PaisDominio.Builder().nombre("C").build();
        PaisDominio dominioPaisCompleto= new PaisDominio.Builder().nombre("C").id(UtilId.generarId()).build();

    }


}
