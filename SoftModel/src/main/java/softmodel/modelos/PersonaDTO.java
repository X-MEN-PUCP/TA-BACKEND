/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softmodel.modelos;

import softmodel.util.Genero;
import java.util.Date;



/**
 *
 * @author Mcerv
 */
public class PersonaDTO {
    private Integer idPersona;
    private CuentaDTO cuenta;
    private String nombres;
    private String apellido_peterno;
    private String apellido_materno;
    private Date fechaNaciemiento;
    private String correoElectronico;
    private String numCelular;
    private Genero genero;

    /**
     * @return the idPersona
     */
    public Integer getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the cuenta
     */
    public CuentaDTO getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellido_paterno() {
        return apellido_peterno;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_peterno = apellido_paterno;
    }

    /**
     * @return the apellido_materno
     */
    public String getApellido_materno() {
        return apellido_materno;
    }
    /**
     * @param apellido_materno the apellido_materno to set
     */
    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }


    /**
     * @return the fechaNaciemiento
     */
    public Date getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    /**
     * @param fechaNaciemiento the fechaNaciemiento to set
     */
    public void setFechaNaciemiento(Date fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
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
     * @return the numCelular
     */
    public String getNumCelular() {
        return numCelular;
    }

    /**
     * @param numCelular the numCelular to set
     */
    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    /**
     * @return the genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    
    public void copiarDesde(PersonaDTO otra) {
        this.idPersona = otra.getIdPersona();
        this.cuenta = otra.getCuenta();
        this.nombres = otra.getNombres();
        this.apellido_peterno = otra.getApellido_paterno();
        this.apellido_materno = otra.getApellido_materno();
        this.fechaNaciemiento = otra.getFechaNaciemiento();
        this.correoElectronico = otra.getCorreoElectronico();
        this.numCelular = otra.getNumCelular();
        this.genero = otra.getGenero();
    }
    
    
}
