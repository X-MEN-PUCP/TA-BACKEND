/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.softws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.xml.ws.WebServiceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbusiness.bo.LogIn;
import pe.edu.pucp.softbusiness.bo.citaBO;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.CuentaDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;

/**
 *
 * @author Cesar
 */
@WebService(serviceName = "LoginWS")
public class LoginWS {

    private LogIn loginBO;
    
    public LoginWS(){
        loginBO=new LogIn();
    }

    @WebMethod(operationName = "iniciarSesion")
    public CuentaDTO iniciarSesion(
        @WebParam(name = "numeroDoc") String numeroDoc,
        @WebParam(name = "contrasenha") String contrasenha
    ) {
        return loginBO.iniciarSesion(numeroDoc, contrasenha);
    }
}
