package co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler.impl;

import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.entidad.PaisEntidad;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.asembler.EntidadAssembler;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;

public class PaisEntidadAssembler implements EntidadAssembler<PaisDominio, PaisEntidad> {

    private final EntidadAssembler<PaisDominio, PaisEntidad> instancia = new PaisEntidadAssembler();
    private PaisEntidadAssembler(){}

    public static EntidadAssembler<PaisDominio, PaisEntidad> getInstance() {
        return instancia;
    }

    @Override
    public PaisEntidad convertirAEntidad(PaisDominio dominio) {
        var dominioTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio,new PaisDominio.Builder().build());
        return new PaisEntidad(dominioTmp.getId(), dominioTmp.getNombre());
    }

    @Override
    public PaisDominio convertirADominio(PaisEntidad entidad) {
        var entidadTmp= UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new PaisEntidad().Builder().build());


        return new PaisDominio().Builder()
                .id(entidadTmp.getId())
                .nombre(entidadTmp.getNombre())
                .build();
    }
}
