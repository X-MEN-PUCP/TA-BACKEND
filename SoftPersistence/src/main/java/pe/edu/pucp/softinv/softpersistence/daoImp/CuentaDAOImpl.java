/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import com.mycompany.softmodel.CuentaDTO;
import pe.edu.pucp.softinv.softpersistence.dao.LogInDAO;
import pe.edu.pucp.softinv.softpersistence.daoImp.Util.Columna;

/**
 *
 * @author Mcerv
 */
public class CuentaDAOImpl extends DAOImplBase implements LogInDAO{
    private CuentaDTO cuenta;
    
    public CuentaDAOImpl() {
        super("Cuenta");
        this.retornarLlavePrimaria = true;
        this.cuenta = null;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("ID_CUENTA", true, true));
        this.listaColumnas.add(new Columna("DNI", false, false));
        this.listaColumnas.add(new Columna("CONTRASENHA", false, false));
        this.listaColumnas.add(new Columna("ROL", false, false));
    }
    
//    public CuentaDTO buscarPorDNI(Integer dni){
//        
//    }
}
