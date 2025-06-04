/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

import org.junit.jupiter.api.Test;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softbusiness.bo.LogIn;

/**
 *
 * @author salva
 */
public class loginDefinitivo {
    
    @Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        String dni = "77751498";
        String contrasenha = "pass2";
         CuentaDTO cuenta=null;
        LogIn instance = new LogIn();
        cuenta = instance.iniciarSesion(dni, contrasenha);
        if(cuenta!=null){
            System.out.println("Bienvenido " +  cuenta.getRol());
            
        }else{
            System.out.println("No se pudo inicar sesion");
        }
    }
    
}
