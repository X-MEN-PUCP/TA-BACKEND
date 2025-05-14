/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softpersistence.daoImp;

import java.sql.Date;
//import java.util.Date;
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
import softmodel.modelos.PacienteDTO;
import softmodel.util.Estado;

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
        this.listaColumnas.add(new Columna("estado", false, false));
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
            this.cita = new CitaDTO();
            this.cita.setIdCita(this.resultSet.getInt("id_cita"));
            //Horario
            HorarioDTO horario = new HorarioDTO();
            horario.setIdHorario(this.resultSet.getInt("id_horario"));
            this.cita.setHorario(horario);
            //Medico
            MedicoDTO medico = new MedicoDTO();
            medico.setIdPersona(this.resultSet.getInt("id_medico"));
            this.cita.setMedico(medico);

            this.cita.setObservacionesMedicas(this.resultSet.getString("observaciones_medicas"));
            //Historia
            HistoriaClinicaDTO historia = new HistoriaClinicaDTO();
            historia.setIdHistoriaClinica(this.resultSet.getInt("id_historia"));
            this.cita.setHistoriaClinicaPaciente(historia);
            String estado = this.resultSet.getString("estado");
            this.cita.setEstado(Estado.valueOf(estado.toUpperCase()));
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
    public ArrayList<CitaDTO> listarPorIdMedico(Integer idMedico) {
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

    private String generarSQLParaListarCitasPorMedicoEstadoYFecha() {
        /*SELECT c.id_cita, c.id_horario, c.id_medico, c.observaciones_medicas, c.id_historia, c.estado
            FROM Cita c JOIN Horario h ON c.id_horario = h.id_horario
            WHERE c.id_medico = ? AND c.estado = ? AND DATE(h.fecha) = ?*/
        String sql = "SELECT ";
        String sql_columnas = "";
        String sql_predicado = "";
        sql_predicado = sql_predicado.concat("c.id_medico = ? AND c.estado = ? AND DATE(h.fecha) = ?");
        for (Columna columna : this.listaColumnas) {
            if (!sql_columnas.isBlank()) {
                sql_columnas = sql_columnas.concat(", ");
            }
            sql_columnas = sql_columnas.concat("c.");
            sql_columnas = sql_columnas.concat(columna.getNombre());
        }
        sql = sql.concat(sql_columnas);
        sql = sql.concat(" FROM ");
        sql = sql.concat(this.nombre_tabla);
        sql = sql.concat(" c JOIN Horario h ON c.id_horario = h.id_horario");
        sql = sql.concat(" WHERE ");
        sql = sql.concat(sql_predicado);
        return sql;
    }

    @Override
    public ArrayList<CitaDTO> listarPorIdMedicoEstadoFecha(Integer idMedico, Estado estado, java.util.Date fecha) {
        String sql = this.generarSQLParaListarCitasPorMedicoEstadoYFecha();
        try {
            this.statement.setString(2, estado.toString());
            Date sqlDate = new Date(fecha.getTime());
            this.statement.setDate(3, sqlDate);
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, idMedico, incluirValorDeParametros, parametros);
    }

    @Override
    public ArrayList<CitaDTO> ReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin) {

        ArrayList<CitaDTO> lista = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM Tabla");
            List<Object> parametros = new ArrayList<>();
            List<String> condiciones = new ArrayList<>();

            if (especialidad != null) {
                condiciones.add("id = ?");
                parametros.add(especialidad);
            }
            if (estado != null) {
                condiciones.add("nombre = ?");
                parametros.add(estado);
            }
            if (fechaInicio != null && fechaFin != null) {
                condiciones.add("fecha BETWEEN ? AND ?");
                parametros.add(new java.sql.Date(fechaInicio.getTime()));
                parametros.add(new java.sql.Date(fechaFin.getTime()));
            }

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ");
                sql.append(String.join(" AND ", condiciones));
            }

            this.conexion = DBManager.getInstance().getConnection();
            this.statement = this.conexion.prepareCall(sql.toString());

            for (int i = 0; i < parametros.size(); i++) {
                this.statement.setObject(i + 1, parametros.get(i));
            }

            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                this.instanciarObjetoDelResultSet();
                lista.add(cita);
            }

        } catch (SQLException ex) {
            System.err.println("Error al intentar ReporteResumenGeneral - " + ex);
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
    public ArrayList<CitaDTO> listarPorMedico(int idMedico){
        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_medico");//Nombre columna
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, idMedico, incluirValorDeParametros, parametros);
    }

    @Override
    public ArrayList<CitaDTO> listarPorPaciente(PacienteDTO paciente) {
        ArrayList<CitaDTO> lista = new ArrayList<>();
        CitaDTO cita = new CitaDTO();
        try {
            this.conexion = DBManager.getInstance().getConnection();
            String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_historia");//Nombre columna
            this.statement = this.conexion.prepareCall(sql);
            int idHistoria = paciente.getHistoriaClinica().getIdHistoriaClinica();
            this.statement.setInt(1, idHistoria);
            this.resultSet = this.statement.executeQuery();
            while (this.resultSet.next()) {
                
                instanciarObjetoDelResultSet();
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

}
