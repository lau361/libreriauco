package co.edu.uco.libreriauco.libreriauco.negocio.negocio.reglas;

public interface Rule<O> {
    void ejecutar(O...datos); //puedo recibir uno o muchos

}
