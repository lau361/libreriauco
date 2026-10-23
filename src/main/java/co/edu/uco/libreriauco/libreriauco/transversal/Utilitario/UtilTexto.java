package co.edu.uco.libreriauco.libreriauco.transversal.Utilitario;

public class UtilTexto {
    private static UtilTexto instancia;
    public static final String vacia = "";
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
        return vacia.equals(quitarEspaciosEnBlanco(texto));
    }


    public static String obtenerValorDefecto(String valor, String valorDefecto) {
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }

    public boolean formatoEsValido(String valor, String patron){
       return obtenerValorDefecto(valor).matches(obtenerValorDefecto(patron));
    }


    public  String obtenerValorDefecto(String valor){
        return obtenerValorDefecto(valor,vacia);
    }

    public String quitarEspaciosEnBlanco(String valor){
        return obtenerValorDefecto(valor).trim();
    }

    public int obtenerLongitudCadena(String valor){
        return obtenerValorDefecto(valor).length();
    }
    public int obtenerLongitudCadena(String valor, boolean quitarEspaciosEnBlanco){

        return quitarEspaciosEnBlanco ?
                obtenerLongitudCadena(quitarEspaciosEnBlanco(valor)): obtenerLongitudCadena(valor);
    }
    public boolean longitudCadenaEsValida(String valor, int longitudInicial , int longitudFinal, boolean quitarEspaciosEnBlanco){

        var valorSanitizado = quitarEspaciosEnBlanco ? quitarEspaciosEnBlanco(valor): valor;

        return obtenerLongitudCadena(valorSanitizado)>= longitudInicial && obtenerLongitudCadena(valorSanitizado)<=longitudFinal;
    }

    public String concatenar(Object...valores){
        var resultado= new StringBuilder();
        for(var valor:valores){
            resultado.append(UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, vacia));
        }
        return  resultado.toString();
    }

}