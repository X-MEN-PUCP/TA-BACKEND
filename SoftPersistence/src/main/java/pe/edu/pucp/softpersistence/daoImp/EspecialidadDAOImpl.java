/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softdbmanager.db.DBManager;
import pe.edu.pucp.softmodel.modelos.EspecialidadDTO;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import pe.edu.pucp.softpersistence.dao.EspecialidadDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class EspecialidadDAOImpl extends DAOImplBase implements EspecialidadDAO{
    
    EspecialidadDTO especialidad;
    private final boolean retornarLlavePrimaria;

    public EspecialidadDAOImpl() {
        super("especialidad");
        this.retornarLlavePrimaria = true;
        this.especialidad = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_especialidad", true, true));
        this.listaColumnas.add(new Columna("nombre_especialidad", false, false));
        this.listaColumnas.add(new Columna("precio_consulta", false, false));
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.especialidad.getNombreEspecialidad());
        this.statement.setDouble(2, this.especialidad.getPrecioConsulta());
    }        
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.statement.setString(1, this.especialidad.getNombreEspecialidad());
        this.statement.setDouble(2, this.especialidad.getPrecioConsulta());   
        this.statement.setInt(3, this.especialidad.getIdEspecialidad());   
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.especialidad.getIdEspecialidad());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1,this.especialidad.getIdEspecialidad());
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() {
        try {
            
            especialidad = new EspecialidadDTO();
            especialidad.setIdEspecialidad(this.resultSet.getInt("id_especialidad"));
            especialidad.setNombreEspecialidad(this.resultSet.getString("nombre_especialidad"));
            especialidad.setPrecioConsulta(this.resultSet.getDouble("precio_consulta"));
            
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void limpiarObjetoDelResultSet() {
        this.especialidad = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.especialidad);
    }

    @Override
    public EspecialidadDTO obtenerPorId(Integer especialidadID) {
        this.especialidad = new EspecialidadDTO();
        this.especialidad.setIdEspecialidad(especialidadID);
        super.obtenerPorId();
        return this.especialidad;
    }

    @Override
    public ArrayList<EspecialidadDTO> listarTodos() {
        return (ArrayList<EspecialidadDTO>) super.listarTodos();
    }
    
    @Override
    public Integer insertar(EspecialidadDTO especialidad) {
        this.especialidad = especialidad;
        return super.insertar();
    }

    @Override
    public Integer modificar(EspecialidadDTO especialidad) {
        this.especialidad = especialidad;
        return super.modificar();
    }

    @Override
    public Integer eliminar(Integer id) {
        this.especialidad = new EspecialidadDTO();
        this.especialidad.setIdEspecialidad(id);
        return super.eliminar();
    }
    
    @Override
    public EspecialidadDTO buscarPorNombre(String nombreEspe){
        try {
            super.abrirConexion();
            String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("nombreEspecialidad");//Nombre columna
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setString(1, nombreEspe);
            System.out.println(sql);

            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Encontró");
                this.instanciarObjetoDelResultSet();
                return especialidad;

            } else {
                System.out.println("No Encontró nada");
                return null;
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar buscarPorNombre - " + ex);
        } finally {
            try {
                super.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return especialidad;
    }
}
