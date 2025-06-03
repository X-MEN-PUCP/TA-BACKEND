/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.util.Genero;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softmodel.modelos.PersonaDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;


import pe.edu.pucp.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softdbmanager.db.DBManager;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class MedicoDAOImpl extends DAOImplBase implements MedicoDAO {
    
    
    private MedicoDTO medico;
    
    public MedicoDAOImpl() {
        super("persona");
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
        this.listaColumnas.add(new Columna("correo_electronico", false, false));
        this.listaColumnas.add(new Columna("num_Celular", false, false));
        this.listaColumnas.add(new Columna("genero", false, false));
        this.listaColumnas.add(new Columna("id_especialidad", false, false));        
        this.listaColumnas.add(new Columna("cod_medico", false, false));        
        this.listaColumnas.add(new Columna("id_cuenta", false, false));
    }
    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        java.util.Date fechaNacimientoUtil = this.medico.getFechaNaciemiento();
        java.sql.Date fechaSQL = new java.sql.Date(fechaNacimientoUtil.getTime());
        this.statement.setString(1, this.medico.getNombres()); 
        this.statement.setString(2, this.medico.getApellido_paterno());
        this.statement.setString(3, this.medico.getApellido_materno());
        this.statement.setDate(4, fechaSQL);
        this.statement.setString(5, this.medico.getCorreoElectronico());
            this.statement.setString(6, this.medico.getNumCelular());
        this.statement.setString(7, this.medico.getGenero().toString());
        this.statement.setInt(8, this.medico.getEspecialidad().getIdEspecialidad());
        this.statement.setInt(9, this.medico.getCodMedico());
        this.statement.setInt(10, this.medico.getCuenta().getIdCuenta());
    }
    
    
    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.medico.getIdPersona());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.medico.getIdPersona());
    }

    @Override
    protected void instanciarObjetoDelResultSet(){
        try{
        medico = new MedicoDTO();
        PersonaDTO cuentaVar = new PersonaDTO();
        cuentaVar = this.cargarLecturaPersona();
        medico.copiarDesde(cuentaVar);
        EspecialidadDTO especialidad= new EspecialidadDTO();
        EspecialidadDAOImpl espdao = new EspecialidadDAOImpl();
        especialidad = espdao.obtenerPorId(this.resultSet.getInt("id_especialidad"));
        medico.setEspecialidad(especialidad);
        medico.setCodMedico(this.resultSet.getInt("cod_medico"));
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.medico = null;
    }
    
    @Override
    public MedicoDTO buscarPorIdCuenta(int idCuenta) {
        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_cuenta");//Nombre columna
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        ArrayList<MedicoDTO> medicos = (ArrayList<MedicoDTO>) super.listarTodos(sql, idCuenta, incluirValorDeParametros, parametros);
        if(medicos.size()>0)
            return medicos.get(0);
        return medico;
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
        this.medico = new MedicoDTO();
        this.medico.setIdPersona(id);
        super.obtenerPorId();
        return this.medico;
    }
    
    @Override
    public Integer insertar(MedicoDTO medico) {
        this.medico = medico;
        return super.insertar();
    }
    
    
    public Integer eliminar(Integer id) {
        this.medico = new MedicoDTO();
        this.medico.setIdPersona(id);
        return super.eliminar();
    }
}
