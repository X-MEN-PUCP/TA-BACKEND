/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import java.sql.Date;
//import java.util.Date;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.HistoriaClinicaDTO;
import pe.edu.pucp.softmodel.modelos.HorarioDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.Columna;
import pe.edu.pucp.softdbmanager.db.DBManager;
import pe.edu.pucp.softmodel.modelos.PacienteDTO;
import pe.edu.pucp.softmodel.util.Estado;
import pe.edu.pucp.softpersistence.dao.HistoriaClinicaDAO;
import pe.edu.pucp.softpersistence.dao.HorarioDAO;
import pe.edu.pucp.softpersistence.dao.MedicoDAO;
import pe.edu.pucp.softpersistence.dao.PacienteDAO;
import pe.edu.pucp.softpersistence.daoImp.Util.ParametrosCita;
import pe.edu.pucp.softpersistence.daoImp.Util.ParametrosCitaBuilder;


public class CitaDAOImpl extends DAOImplBase implements CitaDAO {

    private CitaDTO cita;

    public CitaDAOImpl() {
        super("cita");
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
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.cita.getHorario().getIdHorario());
        this.statement.setInt(2, this.cita.getMedico().getIdPersona());
        this.statement.setNull(3, java.sql.Types.VARCHAR);
        this.statement.setNull(4, java.sql.Types.INTEGER);
        this.statement.setString(5, this.cita.getEstado().toString());      
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {

        this.statement.setInt(1, this.cita.getHorario().getIdHorario());
        this.statement.setInt(2, this.cita.getMedico().getIdPersona());
        //Puede que solo se desee modificar el horario o el médico
        if(this.cita.getObservacionesMedicas() != null){
            this.statement.setString(3, this.cita.getObservacionesMedicas());
        }else{
            this.statement.setNull(3, java.sql.Types.VARCHAR);
        }
        if (this.cita.getHistoriaClinicaPaciente() != null){
            this.statement.setInt(4, this.cita.getHistoriaClinicaPaciente().getIdHistoriaClinica());
        } else {
            this.statement.setNull(4, java.sql.Types.INTEGER);
        }
        this.statement.setString(5, this.cita.getEstado().toString());
        this.statement.setInt(6, this.cita.getIdCita());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.cita.getIdCita());
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.cita.getIdCita());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.cita = new CitaDTO();
        this.cita.setIdCita(this.resultSet.getInt("id_cita"));
        //Horario
        HorarioDTO horario;
        HorarioDAO daoHor = new HorarioDAOImpl();
        horario = daoHor.obtenerPorId(this.resultSet.getInt("id_horario"));
        this.cita.setHorario(horario);
        //Medico
        MedicoDTO medico;
        MedicoDAO daoMed = new MedicoDAOImpl();
        medico = daoMed.obtenerPorId(this.resultSet.getInt("id_medico"));
        this.cita.setMedico(medico);

        this.cita.setObservacionesMedicas(this.resultSet.getString("observaciones_medicas"));
        //Historia
        HistoriaClinicaDTO historia;
        HistoriaClinicaDAO daoHistMed = new HistoriaClinicaDAOImpl();
        historia = daoHistMed.obtenerPorId(this.resultSet.getInt("id_historia"));
        this.cita.setHistoriaClinicaPaciente(historia);

        String estado = this.resultSet.getString("estado");
        this.cita.setEstado(Estado.valueOf(estado.toUpperCase()));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.cita = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.cita);
    }

    @Override
    public Integer insertar(CitaDTO cita) {
        this.cita = cita;
        return super.insertar();
    }

    @Override
    public CitaDTO obtenerPorId(Integer citaID) {
        this.cita = new CitaDTO();
        this.cita.setIdCita(citaID);
        super.obtenerPorId();
        return this.cita;
    }

    @Override
    public ArrayList<CitaDTO> listarTodos() {
        return (ArrayList<CitaDTO>) super.listarTodos();
    }

    @Override
    public Integer modificar(CitaDTO cita) {
        this.cita = cita;
        return super.modificar();
    }

    @Override
    public Integer eliminar(Integer id) {
        this.cita = new CitaDTO();
        cita.setIdCita(id);
        return super.eliminar();
    }

    @Override
    public ArrayList<CitaDTO> listarPorIdMedico(Integer idMedico) {
        String sql = super.generarSQLParaListarTodosPorColumnaEspecifica("id_medico");
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, idMedico, incluirValorDeParametros, parametros);
    }

    private String generarSQLParaBuscarCitas(Boolean conEspecialidad,Boolean conCodMedico,Boolean conFecha) {
        /*
        "SELECT c.id_cita, c.id_horario, c.id_medico, c.observaciones_medicas, c.id_historia, c.estado "
                + "FROM cita c "
                + "JOIN horario h ON c.id_horario = h.id_horario "
                + "JOIN persona p ON c.id_medico = p.id_persona "
                + "JOIN especialidad e ON p.id_especialidad = e.id_especialidad "
                + "WHERE c.estado = 'disponible' "
        
        */
        String sql = "SELECT ";
        String sql_columnas = "";
        String sql_predicado = "";
        sql_predicado = sql_predicado.concat(" c.estado = 'disponible' ");
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
        sql = sql.concat(" c JOIN horario h ON c.id_horario = h.id_horario");
        sql = sql.concat(" JOIN persona p ON c.id_medico = p.id_persona");
        sql = sql.concat(" JOIN especialidad e ON p.id_especialidad = e.id_especialidad");
        sql = sql.concat(" WHERE ");
        
        if (conEspecialidad) {
            sql_predicado= sql_predicado.concat("AND e.id_especialidad = ? ");
        }
        if (conCodMedico) {
            sql_predicado= sql_predicado.concat("AND p.cod_medico = ? ");
        }
        if (conFecha) {
            sql_predicado= sql_predicado.concat("AND DATE(h.fecha) = ? ");
        }
        sql = sql.concat(sql_predicado);
        return sql;
    }

    @Override
    public ArrayList<CitaDTO> listarCitasProgramadas(Integer codMedico){
        Integer idEspecialidad = null;
        LocalDate fecha = null;
        return this.buscarCitasDisponibles(idEspecialidad, codMedico, fecha);
    }
    
//    @Override
//    public ArrayList<CitaDTO> listarPorIdMedicoEstadoFecha(Integer idMedico, Estado estado, java.util.Date fecha){
//        String sql = this.generarSQLParaListarCitasPorMedicoEstado();
//        sql = sql.concat("AND DATE(h.fecha) = ?");
//        ParametrosCita parametros = new ParametrosCita(idMedico, estado, fecha);
//        return (ArrayList<CitaDTO>) super.listarTodos(sql, this::incluirValorDeParametros, parametros);
//    }
    
    private void incluirValorDeParametros(Object objetoParametros) {
        List<Object> params = (List<Object>) objetoParametros;
        try {
            for (int i = 0; i < params.size(); i++) {
                this.statement.setObject(i + 1, params.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<CitaDTO> listarPorMedico(int idMedico) {
        String sql = this.generarSQLParaListarTodosPorColumnaEspecifica("id_medico");//Nombre columna
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, idMedico, incluirValorDeParametros, parametros);
    }

    @Override
    public ArrayList<CitaDTO> listarPorPaciente(PacienteDTO paciente) {
        Integer idPaciente = paciente.getIdPersona();
        HistoriaClinicaDAO historiaDao = new HistoriaClinicaDAOImpl();
        HistoriaClinicaDTO historia = historiaDao.obtenerPorIdPaciente(idPaciente);
        int idHistoria = historia.getIdHistoriaClinica();
        String sql = super.generarSQLParaListarTodosPorColumnaEspecifica("id_historia");
        Consumer incluirValorDeParametros = null;
        Object parametros = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, idHistoria, incluirValorDeParametros, parametros);

    }

    @Override
    public ArrayList<CitaDTO> buscarCitasDisponibles(Integer idEspecialidad, Integer codMedico, LocalDate fecha) {
        Boolean conEspecialidad = (idEspecialidad!=null);
        Boolean conCodMedico = (codMedico!=null);
        Boolean conFecha = (fecha!=null);
        
        String sql = this.generarSQLParaBuscarCitas(conEspecialidad, conCodMedico, conFecha);
        Date fechaD = (fecha!=null?Date.valueOf(fecha):null);
        List<Object> params = new ArrayList<>();

        if (idEspecialidad != null)
            params.add(idEspecialidad);
        if (codMedico != null) 
            params.add(codMedico);
        if (fecha != null) 
            params.add(fechaD);
        
        Integer id = null;
        return (ArrayList<CitaDTO>) super.listarTodos(sql, id, this::incluirValorDeParametros, params);
    }
    
        @Override
    public ArrayList<CitaDTO> ReporteResumenGeneral(Integer especialidad, Estado estado, java.util.Date fechaInicio, java.util.Date fechaFin) {

        ArrayList<CitaDTO> lista = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT c.* FROM Cita c "
                    + "JOIN Horario h ON c.id_horario = h.id_horario "
                    + "JOIN Persona p ON c.id_medico = p.id_persona "
                    + "WHERE 1=1");
            List<Object> parametros = new ArrayList<>();
            List<String> condiciones = new ArrayList<>();

            if (especialidad != null) {
                condiciones.add(" AND p.id_especialidad  = ?");
                parametros.add(especialidad);
            }
            if (estado != null) {
                condiciones.add("  AND c.estado = ?");
                parametros.add(estado);
            }
            if (fechaInicio != null && fechaFin != null) {
                condiciones.add(" AND h.fecha BETWEEN ? AND ? ");
                parametros.add(new java.sql.Date(fechaInicio.getTime()));
                parametros.add(new java.sql.Date(fechaFin.getTime()));
            }

            sql.append(String.join(" ", condiciones));

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

}
