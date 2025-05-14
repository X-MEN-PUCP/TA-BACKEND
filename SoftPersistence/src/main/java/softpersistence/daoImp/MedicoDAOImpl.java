/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import softmodel.modelos.EspecialidadDTO;
import softmodel.util.Genero;
import softmodel.modelos.MedicoDTO;
import softmodel.modelos.PersonaDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


import softpersistence.dao.MedicoDAO;
import softdbmanager.DBManager;
import softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class MedicoDAOImpl extends DAOImplBase implements MedicoDAO {
    
    
    MedicoDTO medico;
    
    public MedicoDAOImpl() {
        super("Persona");
        this.retornarLlavePrimaria = true;
        this.medico = null;
    }
    

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_persona", true, true));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellido_paterno", false, false));
        this.listaColumnas.add(new Columna("apellido_materno", false, false));
        this.listaColumnas.add(new Columna("fecha_nacimiento", false, false));
        this.listaColumnas.add(new Columna("correoElectronico", false, false));
        this.listaColumnas.add(new Columna("num_Celular", false, false));
        this.listaColumnas.add(new Columna("genero", false, false));
        this.listaColumnas.add(new Columna("id_especialidad", false, false));        
        this.listaColumnas.add(new Columna("cod_medico", false, false));        
        this.listaColumnas.add(new Columna("id_cuenta", false, false));
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        medico = new MedicoDTO();
        PersonaDTO cuentaVar = new PersonaDTO();
        cuentaVar = this.cargarLecturaPersona();
        medico.copiarDesde(cuentaVar);
        EspecialidadDTO especialidad= new EspecialidadDTO();
        EspecialidadDAOImpl espdao = new EspecialidadDAOImpl();
        especialidad = espdao.obtenerPorId(this.resultSet.getInt("id_especialidad"));
        medico.setEspecialidad(especialidad);
        medico.setCodMedico(this.resultSet.getInt("cod_medico"));
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.medico = null;
    }
    
    @Override
    public MedicoDTO buscarPorIdCuenta(int idCuenta) {
        MedicoDTO cuentaVar = null;
        try {
            this.abrirConexion();
            String sql = "SELECT id_persona, id_especialidad FROM Persona WHERE id_cuenta = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idCuenta);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
            }else{
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId en Medico- " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return cuentaVar;
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.medico);
    }

    @Override
    public ArrayList<MedicoDTO> listarPorIdEspecialidad(int idEspecialidad){
        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_Especialidad");//Nombre columna
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<MedicoDTO>) super.listarTodos(sql, idEspecialidad, incluirValorDeParametros, parametros);
    }
    
    @Override
    public MedicoDTO obtenerPorId(Integer id) {
        System.out.println(id);
        try {
            super.abrirConexion();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            System.out.println(sql);

            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Encontró");
                this.instanciarObjetoDelResultSet();
                return medico;

            } else {
                System.out.println("No Encontró nada");
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                super.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return medico;
    }
    
}
