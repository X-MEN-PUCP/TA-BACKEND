/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import pe.edu.pucp.softmodel.util.Genero;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.modelos.PersonaDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softpersistence.dao.PacienteDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;

/**
 *
 * @author Mcerv
 */
public class PacienteDAOImpl extends DAOImplBase implements PacienteDAO{
    private PacienteDTO paciente;
    
    public PacienteDAOImpl(){
        super("persona");
    }
    
    @Override
    protected void configurarListaDeColumnas(){
        this.listaColumnas.add(new Columna("id_persona", true, true));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellido_paterno", false, false));
        this.listaColumnas.add(new Columna("apellido_materno", false, false));
        this.listaColumnas.add(new Columna("fecha_nacimiento", false, false));
        this.listaColumnas.add(new Columna("correo_electronico", false, false));
        this.listaColumnas.add(new Columna("num_Celular", false, false));
        this.listaColumnas.add(new Columna("genero", false, false));
        this.listaColumnas.add(new Columna("id_cuenta", false, false));
        //this.listaColumnas.add(new Columna("id_historia", false, false));
        
        
    }
    
    @Override
    public Integer insertar(PacienteDTO paciente) {
        this.paciente = paciente;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {
           //historia clinica debe exitir antes
            java.util.Date fechaNacimientoUtil = this.paciente.getFechaNaciemiento();
            java.sql.Date fechaSQL = new java.sql.Date(fechaNacimientoUtil.getTime());
            this.statement.setString(2, this.paciente.getNombres());
            this.statement.setString(3, this.paciente.getApellido_paterno());
            this.statement.setString(4, this.paciente.getApellido_materno());
            this.statement.setDate(5, fechaSQL);  
            this.statement.setString(6, this.paciente.getCorreoElectronico());
            this.statement.setString(7, this.paciente.getNumCelular());
            this.statement.setString(8, this.paciente.getGenero().toString());
            this.statement.setInt(9, this.paciente.getCuenta().getIdCuenta());
            //this.statement.setInt(10, this.paciente.getHistoriaClinica().getIdHistoriaClinica());
            
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.paciente.getIdPersona());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException{
        PersonaDTO cuentaVar = this.cargarLecturaPersona();
        paciente = new PacienteDTO();
        paciente.copiarDesde(cuentaVar);
        //HistoriaClinicaDTO historia = new HistoriaClinicaDTO();
        //historia.setIdHistoriaClinica(this.resultSet.getInt("id_historia"));
        //paciente.setHistoriaClinica(historia);
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.paciente = null;
    }
    
    @Override
    public PacienteDTO buscarPorIdCuenta(int idCuenta){
        try {
            System.out.println("Buscando paciente...");
            this.abrirConexion();
            //String sql = "SELECT id_persona, id_historia FROM Persona WHERE id_cuenta = ?";
            String sql = "SELECT * FROM Persona WHERE id_cuenta = ?";
            this.colocarSQLenStatement(sql);
            this.statement.setInt(1, idCuenta);
            this.ejecutarConsultaEnBD();
            if (this.resultSet.next()) {
                instanciarObjetoDelResultSet();
            } else {
                limpiarObjetoDelResultSet();
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
        System.out.println("Paciente encontrada");
        return this.paciente;
    }
    
    @Override
    public PacienteDTO obtenerPorId(Integer personaId){
        this.paciente = new PacienteDTO();
        this.paciente.setIdPersona(personaId);
        super.obtenerPorId();
        return this.paciente;
    }
}
