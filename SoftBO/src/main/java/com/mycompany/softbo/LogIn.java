/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softbo;

import com.mycompany.softmodel.CuentaDTO;
import com.mycompany.softmodel.Rol;
import pe.edu.pucp.softinv.softpersistence.dao.CuentaDAO;
import pe.edu.pucp.softinv.softpersistence.daoImp.CuentaDAOImpl;
import softdbmanager.util.Cifrado;

/**
 *
 * @author Mcerv
 */
public class LogIn {
    private CuentaDAO cuentaDAO;
    
    public LogIn(){
        cuentaDAO= new CuentaDAOImpl();
    }
    
    public CuentaBO iniciarSesion(Integer dni, String contrasenha){
        CuentaDTO cuenta;
        cuenta= cuentaDAO.buscarPorDNI(dni);
        if(cuenta!=null){
            if(cuenta.getContrasenha().equals(Cifrado.cifrarMD5(contrasenha))){
                //buscar la persona por idCuenta y enviar los id que necesita cada cuenta?
                switch (cuenta.getRol()) {
                    case ADMINISTRADOR:
                        return new CuentaAdmin(cuenta.getIdCuenta());
                    case MEDICO:
                        return new CuentaMedico(cuenta.getIdCuenta());
                    case PACIENTE:
                        return new CuentaPaciente(cuenta.getIdCuenta());
                    default:
                        throw new AssertionError();
                }
            }else{
                System.out.print("Contraseña incorrecta\n");
                return null;
            } 
        }else{
            System.out.println("No existe el usuario");
            return null;
        }
    }
}
