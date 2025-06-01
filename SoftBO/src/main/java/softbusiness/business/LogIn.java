/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softbusiness.business;


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
    
    public CuentaBO iniciarSesion(String numeroDoc, String contrasenha){
        CuentaDTO cuenta;
        cuenta= cuentaDAO.buscarPorNumeroDocumento(numeroDoc);
        if(cuenta!=null){
            if(Cifrado.descifrarMD5(cuenta.getContrasenha()).equals(contrasenha)){
                switch (cuenta.getRol()) {
                    case ADMINISTRADOR:
                        return (CuentaAdmin) new CuentaAdmin(cuenta.getIdCuenta());
                    case MEDICO:
                        return (CuentaMedico) new CuentaMedico(cuenta.getIdCuenta());
                    case PACIENTE:
                        return (CuentaPaciente)new CuentaPaciente(cuenta.getIdCuenta());
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
