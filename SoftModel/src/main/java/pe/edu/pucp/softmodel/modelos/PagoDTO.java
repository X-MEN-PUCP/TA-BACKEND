/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softmodel.modelos;

import pe.edu.pucp.softmodel.util.MetodoPago;
import java.time.LocalDate;
import java.util.Date;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softmodel.util.EstadoPago;

/**
 *
 * @author Mcerv
 */
public class PagoDTO {
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
    private EstadoPago estado;

    public EstadoPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoPago estado) {
        this.estado = estado;
    }

    /**
     * @return the idPago
     */
    public Integer getIdPago() {
        return idPago;
    }

    /**
     * @param idPago the idPago to set
     */
    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    /**
     * @return the cita
     */
    public CitaDTO getCita() {
        return cita;
    }

    /**
     * @param cita the cita to set
     */
    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    /**
     * @return the metodoPago
     */
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    /**
     * @param metodoPago the metodoPago to set
     */
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * @return the titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * @param titular the titular to set
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the numTarjeta
     */
    public String getNumTarjeta() {
        return numTarjeta;
    }

    /**
     * @param numTarjeta the numTarjeta to set
     */
    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    /**
     * @return the fechaExpiracion
     */
    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    /**
     * @return the CVV
     */
    public String getCVV() {
        return CVV;
    }

    /**
     * @param CVV the CVV to set
     */
    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    /**
     * @return the direccionFactura
     */
    public String getDireccionFactura() {
        return direccionFactura;
    }

    /**
     * @param direccionFactura the direccionFactura to set
     */
    public void setDireccionFactura(String direccionFactura) {
        this.direccionFactura = direccionFactura;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the fechaReserva
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * @param fechaReserva the fechaReserva to set
     */
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    
}
