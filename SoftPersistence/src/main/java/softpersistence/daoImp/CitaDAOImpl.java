/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import softmodel.modelos.CitaDTO;
import softmodel.modelos.HistoriaClinicaDTO;
import softmodel.modelos.HorarioDTO;
import softmodel.modelos.MedicoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import softpersistence.dao.CitaDAO;
import softpersistence.daoImp.Util.Columna;
import softdbmanager.DBManager;

/**
 *
 * @author salva
 */
public class CitaDAOImpl extends DAOImplBase implements CitaDAO {

    CitaDTO cita;
    private final boolean retornarLlavePrimaria;

    public CitaDAOImpl() {
        super("CITAS");
        this.retornarLlavePrimaria = true;
        this.cita = null;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id_cita", true, true));
        this.listaColumnas.add(new Columna("id_horario", false, false));
        this.listaColumnas.add(new Columna("id_medico", false, false));
        this.listaColumnas.add(new Columna("observaciones_medicas", false, false));
        this.listaColumnas.add(new Columna("id_historia", false, false));
    }

    @Override
    public Integer insertar(CitaDTO cita) {
        this.cita = cita;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() {
        try {

            this.statement.setInt(1, this.cita.getHorario().getIdHorario());
            this.statement.setInt(2, this.cita.getMedico().getIdPersona());
            this.statement.setString(4, this.cita.getObservacionesMedicas());
            this.statement.setInt(4, this.cita.getHistoriaClinicaPaciente().getIdHistoriaClinica());

        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void incluirValorDeParametrosParaModificacion() {
        try {

            this.statement.setInt(1, this.cita.getHorario().getIdHorario());
            this.statement.setInt(2, this.cita.getMedico().getIdPersona());
            this.statement.setString(4, this.cita.getObservacionesMedicas());
            this.statement.setInt(4, this.cita.getHistoriaClinicaPaciente().getIdHistoriaClinica());

        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void instanciarObjetoDelResultSet() {
        try {
            cita = new CitaDTO();
            cita.setIdCita(this.resultSet.getInt("id_cita"));
            cita.setObservacionesMedicas(this.resultSet.getString("observaciones_medicas"));
            //Horario
            HorarioDTO horario = new HorarioDTO();
            horario.setIdHorario(this.resultSet.getInt("id_horario"));
            cita.setHorario(horario);
            //Medico
            MedicoDTO medico = new MedicoDTO();
            medico.setIdPersona(this.resultSet.getInt("id_medico"));
            cita.setMedico(medico);
            //Historia
            HistoriaClinicaDTO historia = new HistoriaClinicaDTO();
            historia.setIdHistoriaClinica(this.resultSet.getInt("id_historia"));
            cita.setHistoriaClinicaPaciente(historia);

        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CitaDTO obtenerPorId(Integer citaID) {
        System.out.println(citaID);
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaObtenerPorId();
            this.statement = this.conexion.prepareCall(sql);
            this.statement.setInt(1, citaID);
            System.out.println(sql);

            this.resultSet = this.statement.executeQuery();
            if (this.resultSet.next()) {
                System.out.println("Encontró");
                this.instanciarObjetoDelResultSet();
                return cita;

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
        return cita;
    }

    @Override
    public ArrayList<CitaDTO> listarTodos() {
        ArrayList<CitaDTO> lista = new ArrayList<>();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodos();
            this.statement = this.conexion.prepareCall(sql);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
                lista.add(cita);
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
    public Integer modificar(CitaDTO cita) {
        int resultado = 0;
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
    
    @Override
    public ArrayList<CitaDTO> listarPorIdMedico(Integer idMedico){
        String sql = super.generarSQLParaListarTodosPorColumnaEspecifica("id_medico");
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, idMedico, incluirValorDeParametros, parametros);
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.cita);
    }    
}
