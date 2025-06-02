/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

import pe.edu.pucp.softmodel.modelos.AdminDTO;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.modelos.PersonaDTO;

import pe.edu.pucp.softpersistence.dao.AdminDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class AdminDAOImpl extends DAOImplBase implements AdminDAO{

    private AdminDTO admin;

    public AdminDAOImpl() {
        super("persona");
        this.retornarLlavePrimaria = true;
        this.admin = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_persona", true, true));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellido_paterno", false, false));
        this.listaColumnas.add(new Columna("apellido_materno", false, false));
        this.listaColumnas.add(new Columna("fecha_nacimiento", false, false));
        this.listaColumnas.add(new Columna("correo_electronico", false, false));
        this.listaColumnas.add(new Columna("num_Celular", false, false));
        this.listaColumnas.add(new Columna("genero", false, false));      
        this.listaColumnas.add(new Columna("id_cuenta", false, false));
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
