package co.edu.uco.libreriauco.libreriauco.pruebas;

//import co.edu.uco.libreriauco.Utilitario.UtilTexto;
import static co.edu.uco.libreriauco.libreriauco.Utilitario.UtilTexto.getUtilTexto;

public class PruebasTexto {
    public static void main(String[] args) {
        String miVariable = "   mi casa es azul          dx";

        System.out.println(miVariable);
        System.out.println(getUtilTexto().obtenerLongitudCadena(miVariable, false));
        System.out.println(getUtilTexto().obtenerLongitudCadena(miVariable, true));



    }
}

