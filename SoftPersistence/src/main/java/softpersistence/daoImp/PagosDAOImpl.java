/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import softdbmanager.DBManager;

import softmodel.util.*;

import softmodel.modelos.*;

import softpersistence.daoImp.Util.Columna;
import softpersistence.dao.PagosDAO;

/**
 *
 * @author salva
 */
public class PagosDAOImpl extends DAOImplBase implements PagosDAO {

    private PagosDTO pago;

    public PagosDAOImpl() {
        super("Pagos");
        pago = null;

    }


    @Override
    protected void configurarListaDeColumnas(){
        this.listaColumnas.add(new Columna("id_pago", true, true));
        this.listaColumnas.add(new Columna("id_cita", false, false));
        this.listaColumnas.add(new Columna("metodo_de_pago", false, false));
        this.listaColumnas.add(new Columna("titular", false, false));
        this.listaColumnas.add(new Columna("correoElectronico", false, false));
        this.listaColumnas.add(new Columna("numero_tarjeta", false, false));
        this.listaColumnas.add(new Columna("fechaExpiracion", false, false));
        this.listaColumnas.add(new Columna("CVV", false, false));
        this.listaColumnas.add(new Columna("direccionFactura", false, false));
        this.listaColumnas.add(new Columna("monto", false, false));
        this.listaColumnas.add(new Columna("fecha_Reserva", false, false));
        this.listaColumnas.add(new Columna("fecha_Pago", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
           //historia clinica debe exitir antes
            java.util.Date fechaReserva = this.pago.getFechaReserva();
            java.sql.Date fechaSQL = new java.sql.Date(fechaReserva.getTime());
            this.statement.setInt(2, this.pago.getCita().getIdCita());
            this.statement.setString(3, this.pago.getMetodoPago().toString());
            this.statement.setString(4, this.pago.getTitular());
            this.statement.setString(5, this.pago.getCorreoElectronico());
            this.statement.setString(6, this.pago.getNumTarjeta());
            this.statement.setDate(7, null);
            this.statement.setString(8, this.pago.getCVV());
            this.statement.setString(9, this.pago.getDireccionFactura());
            this.statement.setDouble(10, this.pago.getMonto());
            this.statement.setDate(11, fechaSQL);
            this.statement.setDate(12, null);
            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() {
        try {
           //historia clinica debe exitir antes
            java.util.Date fechaReserva = this.pago.getFechaReserva();
            java.sql.Date fechaSQL = new java.sql.Date(fechaReserva.getTime());
            this.statement.setInt(2, this.pago.getCita().getIdCita());
            this.statement.setString(3, this.pago.getMetodoPago().toString());
            this.statement.setString(4, this.pago.getTitular());
            this.statement.setString(5, this.pago.getCorreoElectronico());
            this.statement.setString(6, this.pago.getNumTarjeta());
            this.statement.setDate(7, null);
            this.statement.setString(8, this.pago.getCVV());
            this.statement.setString(9, this.pago.getDireccionFactura());
            this.statement.setDouble(10, this.pago.getMonto());
            this.statement.setDate(11, fechaSQL);
            this.statement.setDate(12, null);
            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException{
        pago = new PagosDTO();
        pago.setIdPago(this.resultSet.getInt("id_pago"));
        CitaDTO cita = new CitaDTO();
        cita.setIdCita(this.resultSet.getInt("id_cita"));
        pago.setCita(cita);
        pago.setMetodoPago(MetodoPago.valueOf(this.resultSet.getString("metodo_de_pago")));
        pago.setTitular(this.resultSet.getString("titular"));
        pago.setCorreoElectronico(this.resultSet.getString("correoElectronico"));
        pago.setNumTarjeta(this.resultSet.getString("numero_tarjeta"));
        pago.setFechaExpiracion(this.resultSet.getDate("fechaExpiracion").toLocalDate());
        pago.setCVV(this.resultSet.getString("CVV"));
        pago.setDireccionFactura(this.resultSet.getString("direccionFactura"));
        pago.setMonto(this.resultSet.getDouble("monto"));
        pago.setFechaReserva(this.resultSet.getDate("fecha_Reserva"));
        pago.setFechaPago(this.resultSet.getDate("fecha_Pago"));


    }


    @Override
    public Integer insertar(PagosDTO pago) {
        // TODO Auto-generated method stub
        this.pago = pago;
        return super.insertar();
    }


    @Override
    public Integer modificar(PagosDTO pago) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.incluirValorDeParametrosParaModificacion();
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar modificar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    @Override
    public PagosDTO buscarPorIdCuenta(int idPago) {
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, idPago);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Encontró");
                this.instanciarObjetoDelResultSet();
                return pago;

            } else {
                System.out.println("No Encontró nada");
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return pago;
    }

    @Override
    public PagosDTO buscarPorIdCita(int idCita) {
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_cita");
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, idCita);
            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Encontró");
                this.instanciarObjetoDelResultSet();
                return pago;

            } else {
                System.out.println("No Encontró nada");
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return pago;
    }

    @Override
    public ArrayList<PagosDTO> listarTodos() {
        ArrayList<PagosDTO> lista = new ArrayList<>();
        PagosDTO pago = new PagosDTO();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                
                instanciarObjetoDelResultSet();
                lista.add(pago);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return lista;
    }

   @Override
    public Integer eliminar(int id){
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaEliminacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar eliminar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
        
    }
    
   
    
}
