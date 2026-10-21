package co.edu.uco.libreriauco.libreriauco.negocio.fachada.asembler.impl;

import co.edu.uco.libreriauco.libreriauco.dominio.PaisDominio;
import co.edu.uco.libreriauco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.libreriauco.negocio.fachada.asembler.DTOAssembler;
import co.edu.uco.libreriauco.libreriauco.transversal.Utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public class PaisDtoAssembler implements DTOAssembler<PaisDominio, PaisDTO> {

    private static final DTOAssembler<PaisDominio, PaisDTO> instancia = new PaisDtoAssembler();
    private PaisDtoAssembler(){}

    public static DTOAssembler<PaisDominio, PaisDTO> getInstance() {
        return instancia;
    }

    @Override
    public PaisDTO convertirADTO(PaisDominio dominio) {
        var dominioTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio,
                new PaisDominio.Builder().build());
        var dto = new PaisDTO();
        dto.setId(dominioTmp.getId());
        dto.setNombre(dominioTmp.getNombre());
        return dto;
    }

    @Override
    public PaisDominio convertirADominio(PaisDTO dto) {
        var dtoTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new PaisDTO());

        return new PaisDominio.Builder().id(dtoTmp.getId()).nombre(dtoTmp.getNombre()).build();
    }

    @Override
    public List<PaisDTO> convertirADTO(List<PaisDominio> listaDominios) {
        var listaTmp = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios,
                new ArrayList<PaisDominio>());

        var listaDto = new ArrayList<PaisDTO>();
        for (var dominio : listaTmp) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
