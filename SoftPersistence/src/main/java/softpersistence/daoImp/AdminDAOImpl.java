/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

import softmodel.modelos.AdminDTO;
import softmodel.modelos.EspecialidadDTO;
import softmodel.modelos.MedicoDTO;
import softmodel.modelos.PersonaDTO;

import softpersistence.dao.AdminDAO;

/**
 *
 * @author salva
 */
public class AdminDAOImpl extends DAOImplBase implements AdminDAO{

    private AdminDTO admin;

    public AdminDAOImpl() {
        super("Persona");
        this.retornarLlavePrimaria = true;
        this.admin = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        admin = new AdminDTO();
        PersonaDTO cuentaVar = new PersonaDTO();
        cuentaVar = this.cargarLecturaPersona();
        admin.copiarDesde(cuentaVar);
        
    }


    @Override
    public AdminDTO buscarPorIdCuenta(int idCuenta) {

        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_cuenta");//Nombre columna
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        ArrayList<AdminDTO> admins = (ArrayList<AdminDTO>) super.listarTodos(sql, idCuenta, incluirValorDeParametros, parametros);
        if(admins.size()>0)
            return admins.get(0);
        return null;

    }
       
    
}
