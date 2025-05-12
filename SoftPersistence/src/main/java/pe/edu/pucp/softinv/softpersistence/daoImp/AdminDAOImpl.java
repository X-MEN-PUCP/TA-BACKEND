/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softinv.softpersistence.daoImp;

import java.sql.SQLException;

import com.mycompany.softmodel.AdminDTO;
import com.mycompany.softmodel.EspecialidadDTO;
import com.mycompany.softmodel.MedicoDTO;
import com.mycompany.softmodel.PersonaDTO;

import pe.edu.pucp.softinv.softpersistence.dao.AdminDAO;

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

        try {
            this.abrirConexion();
            String sql = "SELECT id_persona, id_historia FROM Persona WHERE id_cuenta = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idCuenta);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                admin = null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar buscarPorIdCuenta en Paciente- " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return this.admin;

    }
       
    
}
