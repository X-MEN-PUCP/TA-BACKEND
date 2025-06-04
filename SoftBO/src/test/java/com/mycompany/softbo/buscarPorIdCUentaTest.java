/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

import org.junit.jupiter.api.Test;
import pe.edu.pucp.softbusiness.bo.PacienteBO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;

/**
 *
 * @author Lylli
 */
public class buscarPorIdCUentaTest {
    
    PacienteBO bo = new PacienteBO();
    
    @Test
    public void Test(){
        PacienteDTO pac;
        pac = bo.buscarPorIdCuenta(74073698);
        if(pac)
    }
            
    
}
