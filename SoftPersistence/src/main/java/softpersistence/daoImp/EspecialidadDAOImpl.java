/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import softdbmanager.DBManager;
import softmodel.modelos.EspecialidadDTO;
import softmodel.modelos.HistoriaClinicaDTO;
import softmodel.modelos.HorarioDTO;
import softmodel.modelos.MedicoDTO;
import softpersistence.dao.EspecialidadDAO;
import softpersistence.daoImp.Util.Columna;

/**
 *
 * @author salva
 */
public class EspecialidadDAOImpl extends DAOImplBase implements EspecialidadDAO{
    
    EspecialidadDTO especialidad;
    private final boolean retornarLlavePrimaria;

    public EspecialidadDAOImpl() {
        super("Especialidad");
        this.retornarLlavePrimaria = true;
        this.especialidad = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_especialidad", true, true));
        this.listaColumnas.add(new Columna("nombreEspecialidad", false, false));
        this.listaColumnas.add(new Columna("precio_consulta", false, false));
    }

    @Override
    public Integer insertar(EspecialidadDTO especialidad) {
        this.especialidad = especialidad;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {

            this.statement.setString(1, this.especialidad.getNombreEspecialidad());
            this.statement.setDouble(2, this.especialidad.getPrecioConsulta());            


        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() {
        try {

            this.statement.setString(1, this.especialidad.getNombreEspecialidad());
            this.statement.setDouble(2, this.especialidad.getPrecioConsulta());   
            this.statement.setInt(3, this.especialidad.getIdEspecialidad());   

        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void instanciarObjetoDelResultSet() {
        try {
            
            especialidad = new EspecialidadDTO();
            especialidad.setIdEspecialidad(this.resultSet.getInt("id_especialidad"));
            especialidad.setNombreEspecialidad(this.resultSet.getString("nombreEspecialidad"));
            especialidad.setPrecioConsulta(this.resultSet.getDouble("precio_consulta"));
            
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public EspecialidadDTO obtenerPorId(Integer especialidadID) {
        System.out.println(especialidadID);
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, especialidadID);
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
            System.err.println("Error al intentar obtenerPorId - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return especialidad;
    }

    @Override
    public ArrayList<EspecialidadDTO> listarTodos() {
        ArrayList<EspecialidadDTO> lista = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
                lista.add(especialidad);
            }
        } catch (SQLException ex) {
            System.err.println("Error al intentar listarTodos - " + ex);
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return lista;
    }

    @Override
    public Integer modificar(EspecialidadDTO especialidad) {
        int resultado = 0;
        this.especialidad = especialidad;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaModificacion();
            this.statement = this.conexion.prepareCall(sql);
            this.incluirValorDeParametrosParaModificacion();
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar modificar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }

    @Override
    public Integer eliminar(Integer id) {
        int resultado = 0;
        try {
            this.conexion = DBManager.getInstance().getConnection();
            this.conexion.setAutoCommit(false);
            String sql = this.generarSQLParaEliminacion();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, id);
            resultado = this.statement.executeUpdate();
            this.conexion.commit();
        } catch (SQLException ex) {
            System.err.println("Error al intentar eliminar - " + ex);
            try {
                if (this.conexion != null) {
                    this.conexion.rollback();
                }
            } catch (SQLException ex1) {
                System.err.println("Error al hacer rollback - " + ex1);
            }
        } finally {
            try {
                if (this.conexion != null) {
                    this.conexion.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return resultado;
    }
    
}
