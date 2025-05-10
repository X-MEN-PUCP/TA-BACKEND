/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo.Util;

import com.mycompany.softmodel.CuentaDTO;
import com.mycompany.softmodel.PersonaDTO;
import com.mycompany.softmodel.Util.Genero;
import java.util.Date;

/**
 *
 * @author Mcerv
 */
public class NuevoUsuarioBuilder {
    private Integer dni;
    private String contrasenha;
    
    //los nombres y apellidos se supone que se autocompletan
    //con los datos de la api
    //en la que se busca por numero de dni
    private String correoElectronico;
    private String numCelular;
    private Genero genero;
    
    public NuevoUsuarioBuilder(Integer dni, String contrasenha){
        //se debería buscar en la api aqui llenar los nombres y fecah de nacimiento?
        this.correoElectronico=null;
        this.numCelular=null;
        this.genero=null;
    }
    
    public NuevoUsuarioBuilder conCorreoElectronico(String correoElectronico){
        this.setCorreoElectronico(correoElectronico);
        return this;
    }
    
    public NuevoUsuarioBuilder conNumCelular(String numCelular){
        this.setNumCelular(numCelular);
        return this;
    }
    
    public NuevoUsuarioBuilder conGenero(Genero genero){
        this.setGenero(genero);
        return this;
    }
    
    public PersonaDTO BuilNuevoUsuario(){
        PersonaDTO persona = new PersonaDTO();
        CuentaDTO cuenta= new CuentaDTO();
        cuenta.setDni(this.getDni());
        cuenta.setContrasenha(this.getContrasenha());
        persona.setCorreoElectronico(this.getCorreoElectronico());
        persona.setNumCelular(this.getNumCelular());
        persona.setGenero(this.getGenero());
        persona.setCuenta(cuenta);
        return persona;
    }
    
    /**
     * @return the dni
     */
    public Integer getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    /**
     * @return the contrasenha
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * @param contrasenha the contrasenha to set
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
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
    
}
