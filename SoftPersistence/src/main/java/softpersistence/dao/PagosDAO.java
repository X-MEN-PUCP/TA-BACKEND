/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package softpersistence.dao;


import java.util.ArrayList;
import softmodel.modelos.PagosDTO;

/**
 *
 * @author salva
 */
public interface PagosDAO {
    
    /*
    private Integer idPago;
    private CitaDTO cita;
    private MetodoPago metodoPago;
    private String titular;
    private String correoElectronico;
    private String numTarjeta;
    private LocalDate fechaExpiracion;
    private String CVV;
    private String direccionFactura;
    private double monto; //float?
    private Date fechaReserva;
    private Date fechaPago;

    */
    
    public Integer insertar(PagosDTO pago);
    public Integer modificar(PagosDTO pago);
    public PagosDTO buscarPorIdCuenta(int idPago);
    public PagosDTO buscarPorIdCita(int idCita);
    public ArrayList<PagosDTO> listarTodos();

    
}
