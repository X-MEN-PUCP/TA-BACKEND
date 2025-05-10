/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

/**
 *
 * @author Mcerv
 */
public class CuentaBO {
    private Integer idCuenta;
    private Integer idPersona;
    
    public CuentaBO(){
        this.idCuenta=null;
    }
    
    protected void setIdCuenta(Integer id){
        this.idCuenta=id;
    }
    
    protected void setIdPersona(Integer id){
        this.idPersona=id;
    }
}
