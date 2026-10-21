package co.edu.uco.libreriauco.libreriauco.negocio.fachada.impl;

import co.edu.uco.libreriauco.libreriauco.dao.factoria.DaoFactory;
import co.edu.uco.libreriauco.libreriauco.dao.factoria.impl.SqlServerDAOFactory;
import co.edu.uco.libreriauco.libreriauco.dto.PaisDTO;
import co.edu.uco.libreriauco.libreriauco.negocio.fachada.PaisFachada;
import co.edu.uco.libreriauco.libreriauco.negocio.fachada.asembler.impl.PaisDtoAssembler;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.PaisNegocio;
import co.edu.uco.libreriauco.libreriauco.negocio.negocio.impl.PaisNegocioImpl;
import co.edu.uco.libreriauco.libreriauco.transversal.Catalogo.CatalogoMensajes;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoExcepcion;
import co.edu.uco.libreriauco.libreriauco.transversal.excepciones.LibreriaUcoFachadaExcepcion;

import java.util.List;
import java.util.UUID;

public class PaisFachadaImpl implements PaisFachada {

    private DaoFactory daoFactory;
    private PaisNegocio paisNegocio;

    public PaisFachadaImpl() {
        daoFactory = new SqlServerDAOFactory();
        paisNegocio = new PaisNegocioImpl(daoFactory);
    }

    @Override
    public void registrarInformacionNuevoPais(PaisDTO datos) {
        daoFactory.iniciarTransaccion();
        try {
            var paisDominio = PaisDtoAssembler.getInstance().convertirADominio(datos);
            paisNegocio.registrarInformacionNuevoPais(paisDominio);
            daoFactory.confirmarTransaccion();

        } catch (LibreriaUcoExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_REGISTRANDO_PAIS;
            throw LibreriaUcoFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarInformacionPaisExistente(UUID id, PaisDTO datos) {
        daoFactory.iniciarTransaccion();
        try {
            var paisDominio = PaisDtoAssembler.getInstance().convertirADominio(datos);
            paisNegocio.modificarInformacionPaisExistente(id, paisDominio);
            daoFactory.confirmarTransaccion();

        } catch (LibreriaUcoExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_MODIFICANDO_PAIS;
            throw LibreriaUcoFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaInformacionPaisExistente(UUID id) {
        daoFactory.iniciarTransaccion();
        try {
            paisNegocio.darBajaInformacionPaisExistente(id);
            daoFactory.confirmarTransaccion();

        } catch (LibreriaUcoExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_DANDO_BAJA_PAIS;
            throw LibreriaUcoFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<PaisDTO> consultarPorFiltro(PaisDTO filtro) {
        try {
            var filtroDominio = PaisDtoAssembler.getInstance().convertirADominio(filtro);
            var paisesEncontrados = paisNegocio.consultarPorFiltro(filtroDominio);
            return PaisDtoAssembler.getInstance().convertirADTO(paisesEncontrados);
        } catch (LibreriaUcoExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_PAIS_POR_FILTRO;
            throw LibreriaUcoFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<PaisDTO> consultarTodos() {
        try {
            var paisesEncontrados = paisNegocio.consultarTodos();
            return PaisDtoAssembler.getInstance().convertirADTO(paisesEncontrados);
        } catch (LibreriaUcoExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_TODOS_LOS_PAISES;
            throw LibreriaUcoFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public PaisDTO consultarPorId(UUID id) {
        try {
            var paisEncontrado = paisNegocio.consultarPorId(id);
            return PaisDtoAssembler.getInstance().convertirADTO(paisEncontrado);
        } catch (LibreriaUcoExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.PaisFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_PAIS_POR_ID;
            throw LibreriaUcoFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
