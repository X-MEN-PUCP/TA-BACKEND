/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softmodel.modelos;

import java.sql.Date;


/**
 *
 * @author Mcerv
 */
public class NotificacionesDTO {
    
    int idNotificacion;
    CuentaDTO cuenta;
    CitaDTO cita;
    String tipo;
    String mensaje;
    String canal;
    Date fechaEnvio;

    

    //faltan los atributos
    public NotificacionesDTO(int idNotificacion, CuentaDTO cuenta, CitaDTO cita, String tipo, String mensaje, String canal, Date fechaEnvio) {
        this.idNotificacion = idNotificacion;
        this.cuenta = cuenta;
        this.cita = cita;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.canal = canal;
        this.fechaEnvio = fechaEnvio;
    }
    
    public NotificacionesDTO() {
        this.idNotificacion = 0;
        this.cuenta = null;
        this.cita = null;
        this.tipo = null;
        this.mensaje = null;
        this.canal = null;
        this.fechaEnvio = null;
    }
    
    
    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    public CitaDTO getCita() {
        return cita;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    
    
    
            
    
    
}
