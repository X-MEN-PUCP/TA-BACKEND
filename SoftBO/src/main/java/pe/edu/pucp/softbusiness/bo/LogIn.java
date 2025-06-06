/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softbusiness.bo;


import pe.edu.pucp.softmodel.util.Rol;
import pe.edu.pucp.softpersistence.dao.CuentaDAO;
import pe.edu.pucp.softpersistence.daoImp.CuentaDAOImpl;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softdbmanager.util.Cifrado;

/**
 *
 * @author Mcerv
 */
public class LogIn {
    private CuentaDAO cuentaDAO;
    
    public LogIn(){
        cuentaDAO = new CuentaDAOImpl();
    }
    
    public CuentaDTO iniciarSesion(String numeroDoc, String contrasenha){
        CuentaDTO cuenta;
        cuenta= cuentaDAO.buscarPorNumeroDocumento(numeroDoc);
        System.out.println(">"+ Cifrado.cifrarMD5(contrasenha)+"<");
        if(cuenta!=null){
            if(Cifrado.descifrarMD5(cuenta.getContrasenha()).equals(contrasenha)){
                return cuenta;
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
