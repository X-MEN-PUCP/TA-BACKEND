/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;


import pe.edu.pucp.softmodel.util.MetodoPago;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.PagoDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softdbmanager.db.DBManager;



import pe.edu.pucp.softpersistence.daoImp.Util.Columna;
import pe.edu.pucp.softpersistence.dao.PagoDAO;

/**
 *
 * @author salva
 */
public class PagoDAOImpl extends DAOImplBase implements PagoDAO {

    private PagoDTO pago;

    public PagoDAOImpl() {
        super("pago");
        pago = null;

    }


    @Override
    protected void configurarListaDeColumnas(){
        this.listaColumnas.add(new Columna("id_pago", true, true));
        this.listaColumnas.add(new Columna("id_cita", false, false));
        this.listaColumnas.add(new Columna("metodo_de_pago", false, false));
        this.listaColumnas.add(new Columna("titular", false, false));
        this.listaColumnas.add(new Columna("correo_electronico", false, false));
        this.listaColumnas.add(new Columna("numero_tarjeta", false, false));
        this.listaColumnas.add(new Columna("fecha_expiracion", false, false));
        this.listaColumnas.add(new Columna("CVV", false, false));
        this.listaColumnas.add(new Columna("direccion_factura", false, false));
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
            this.statement.setInt(1, this.pago.getCita().getIdCita());
            if(this.pago.getMetodoPago() != null)this.statement.setString(2, this.pago.getMetodoPago().toString());
            else this.statement.setString(2, null);
            this.statement.setString(3, this.pago.getTitular());
            this.statement.setString(4, this.pago.getCorreoElectronico());
            this.statement.setString(5, this.pago.getNumTarjeta());
            this.statement.setDate(6, null);
            this.statement.setString(7, this.pago.getCVV());
            this.statement.setString(8, this.pago.getDireccionFactura());
            this.statement.setDouble(9, this.pago.getMonto());
            this.statement.setDate(10, fechaSQL);
            this.statement.setDate(11, null);
            
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
            this.statement.setInt(1, this.pago.getCita().getIdCita());
            if(this.pago.getMetodoPago() != null)this.statement.setString(2, this.pago.getMetodoPago().toString());
            else this.statement.setString(2, null);
            this.statement.setString(3, this.pago.getTitular());
            this.statement.setString(4, this.pago.getCorreoElectronico());
            this.statement.setString(5, this.pago.getNumTarjeta());
            this.statement.setDate(6, null);
            this.statement.setString(7, this.pago.getCVV());
            this.statement.setString(8, this.pago.getDireccionFactura());
            this.statement.setDouble(9, this.pago.getMonto());
            this.statement.setDate(10, fechaSQL);
            this.statement.setDate(11, null);
            this.statement.setInt(12, this.pago.getIdPago());
            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException{
        pago = new PagoDTO();
        pago.setIdPago(this.resultSet.getInt("id_pago"));
        CitaDTO cita = new CitaDTO();
        cita.setIdCita(this.resultSet.getInt("id_cita"));
        pago.setCita(cita);
        MetodoPago metodo = null;
        String metodoDePago=this.resultSet.getString("metodo_de_pago");
        if(metodoDePago==null){
            pago.setMetodoPago(metodo);
        }else{
            pago.setMetodoPago(MetodoPago.valueOf(metodoDePago));
        }
        
        pago.setTitular(this.resultSet.getString("titular"));
        pago.setCorreoElectronico(this.resultSet.getString("correoElectronico"));
        pago.setNumTarjeta(this.resultSet.getString("numero_tarjeta"));
        Date fecha = this.resultSet.getDate("fechaExpiracion");
        if(fecha!=null)
            pago.setFechaExpiracion(new java.sql.Date(fecha.getTime()).toLocalDate());
        else
            pago.setFechaExpiracion(null);
        pago.setCVV(this.resultSet.getString("CVV"));
        pago.setDireccionFactura(this.resultSet.getString("direccionFactura"));
        pago.setMonto(this.resultSet.getDouble("monto"));
        pago.setFechaReserva(this.resultSet.getDate("fecha_Reserva"));
        pago.setFechaPago(this.resultSet.getDate("fecha_Pago"));


    }
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.pago.getIdPago());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.pago.getIdPago());
    }
    
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.pago = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.pago);
    }


    @Override
    public Integer insertar(PagoDTO pago) {
        // TODO Auto-generated method stub
        this.pago = pago;
        return super.insertar();
    }


    @Override
    public Integer modificar(PagoDTO pago) {
        this.pago = pago;
        return super.modificar();
    }

    @Override
    public PagoDTO buscarPorId(int idPago) {
        this.pago = new PagoDTO();
        this.pago.setIdPago(idPago);
        super.obtenerPorId();
        return this.pago;
    }

    @Override
    public PagoDTO buscarPorIdCita(int idCita) {
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
    public ArrayList<PagoDTO> listarTodos() {        
        return (ArrayList<PagoDTO>) super.listarTodos();
    }

   @Override
    public Integer eliminar(int id){
        this.pago = new PagoDTO();
        this.pago.setIdPago(id);
        return super.eliminar();        
    }
    
   
    
}
