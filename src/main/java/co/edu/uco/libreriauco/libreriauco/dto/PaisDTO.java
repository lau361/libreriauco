package co.edu.uco.libreriauco.libreriauco.dto;

import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilId;
import co.edu.uco.libreriauco.libreriauco.crosscuting.Utilitario.UtilTexto;

import java.util.UUID;

public class PaisDTO {
    //Un pais debe podr tener un nombre y un id que es el identificador propio
    //para cada uno de esos yo debo crear una clase aparte con el metodo de devolver valor por defecto
    //en caso de que no me ingrese nada con el fin de evitar los nulls

    // es mejor que sea private , para asi poder hacer el control de un valor que no quiero
    //en el set
    private UUID id;
    private String nombre;

    //deberia ser el objeto antinull , obligue a que el nulo no se pueda colocar

    //definir valor por defecto para pais

    public PaisDTO(){
        setId(UtilId.valorDefecto(id));//lo dejo sin id
        setNombre(UtilTexto.vacia);
    }
    //get and set de id
    // el get me permite leer la informacion y el set es para asignar o cambiar un valor al atributp
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id =UtilId.valorDefecto(id);
    }
    //get and set de nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        //Hago control , ent no quiero que un nombre de un pasi tenga espacios en blanco al inicio o al final
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }
}
