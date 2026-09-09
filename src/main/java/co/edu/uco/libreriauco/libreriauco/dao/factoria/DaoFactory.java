package co.edu.uco.libreriauco.libreriauco.dao.factoria;

import co.edu.uco.libreriauco.libreriauco.dao.entidad.DepartamentoDao;
import co.edu.uco.libreriauco.libreriauco.dao.entidad.PaisDAO;

import java.sql.Connection;

public abstract class DaoFactory {
    private Connection conexion;

    protected DaoFactory(Connection conexion) {
        abrirConexion();
    }

     protected Connection getConexion() {
        return conexion;
    }

    protected void setConexion(Connection conexion) {
        //Tarea :Asegurar que la conexion este abierta y sea valida
        this.conexion = conexion;
    }
    //abstrat para que cada base de datos lo implemente de forma diferente
    protected abstract void abrirConexion();

    public void cerrarConexion(){
        //Tarea Como se cierra ña conexion de forma segura

    }
    public void iniciarTransaccion(){
        //Tarea Como se inicia una transaccion de forma segura
    }
    public void confirmarTransaccion(){
        //Tarea Como se confirma una transaccion de forma segura
    }
    public void cancelarTransaccion(){
        //Tarea Como se cancela una transaccion de forma segura
    }
    // va a fabricar los daos para las entidades
    public abstract PaisDAO obtenerPaisDAO();
    public abstract DepartamentoDao obtenerDepartamentoDAO();

}
