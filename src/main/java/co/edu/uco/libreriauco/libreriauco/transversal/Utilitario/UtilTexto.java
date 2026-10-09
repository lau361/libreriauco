package co.edu.uco.libreriauco.libreriauco.transversal.Utilitario;

public class UtilTexto {
    private static UtilTexto instancia;
    public static String vacia = "";
    public static final String SOLO_LETRAS_Y_ESPACIOS = "^[a-zA-Z ÑñáÁéÉíÍóÓúÚ]*$";
    private UtilTexto() {
    }


    public static UtilTexto getUtilTexto() {

            synchronized (UtilTexto.class) {
                //pregunta si instancia es nula ent se crea si no , la retorn
                if (UtilObjeto.esNulo(instancia)) {
                    instancia = new UtilTexto();
                }
            }
            return instancia;
    }



    public boolean esNula(String texto) {
        return UtilObjeto.esNulo(texto);
    }

    public boolean esVacia(String texto) {
        if(esNula(texto)){
            texto= vacia;
        }
        return vacia.equals(texto);
    }


    public static String obtenerValorDefecto(String valor, String valorDefecto) {
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }

    public boolean formatoEsValido(String valor, String patron){
       return obtenerValorDefecto(valor).matches(obtenerValorDefecto(patron));
    }


    //cuando no me ingresan el valor por defecto , ent el valor defecto va a ser vacia
    public  String obtenerValorDefecto(String valor){
        return obtenerValorDefecto(valor,vacia);
    }
    //aqui se hace ese valor por defecto para que si el valor es null , esto me devuelva vacia

    public String quitarEspaciosEnBlanco(String valor){
        return obtenerValorDefecto(valor).trim();
    }

    public int obtenerLongitudCadena(String valor){
        return obtenerValorDefecto(valor).length();
    }
     //la persona entre por consola y dice si si o si no
    public int obtenerLongitudCadena(String valor, boolean quitarEspaciosEnBlanco){

        return quitarEspaciosEnBlanco ?
                obtenerLongitudCadena(quitarEspaciosEnBlanco(valor)): obtenerLongitudCadena(valor);
    }

     //condición ? si_es_true : si_es_false
    public boolean longitudCadenaEsValida(String valor, int longitudInicial , int longitudFinal, boolean quitarEspaciosEnBlanco){

        var valorSanitizado = quitarEspaciosEnBlanco ? quitarEspaciosEnBlanco(valor): valor;

        return obtenerLongitudCadena(valorSanitizado)>= longitudInicial && obtenerLongitudCadena(valorSanitizado)<=longitudFinal;
    }

}