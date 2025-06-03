package pe.edu.pucp.softws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.softbusiness.bo.MedicoBO;
import pe.edu.pucp.softmodel.modelos.CitaDTO;
import pe.edu.pucp.softmodel.modelos.MedicoDTO;

@WebService(serviceName = "MedicoWS")
public class MedicoWS {

    private final MedicoBO medicoBo;

    public MedicoWS() {
        medicoBo = new MedicoBO();
    }

    @WebMethod(operationName = "buscarPorIdCuenta")
    public MedicoDTO buscarPorIdCuenta(@WebParam(name = "idCuenta") int idCuenta) {
        return medicoBo.buscarPoridCuenta(idCuenta);
    }

    @WebMethod(operationName = "insertarMedico")
    public int insertarMedico(
            @WebParam(name = "idCuenta") int idCuenta,
            @WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidoPaterno") String apPaterno,
            @WebParam(name = "apellidoMaterno") String apMaterno,
            @WebParam(name = "fechaNacimiento") Date fechaNacimiento,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "genero") String genero,
            @WebParam(name = "idEspecialidad") int idEspecialidad,
            @WebParam(name = "codMedico") int codMedico
    ) {
        return medicoBo.insertar(idCuenta, nombres, apPaterno, apMaterno,
                                 fechaNacimiento, correo, genero, idEspecialidad, codMedico);
    }

    @WebMethod(operationName = "listarPorIdEspecialidad")
    public ArrayList<MedicoDTO> listarPorIdEspecialidad(@WebParam(name = "idEspecialidad") int idEspecialidad) {
        return medicoBo.listarPorIdEspecialidad(idEspecialidad);
    }

    @WebMethod(operationName = "obtenerMedicoPorId")
    public MedicoDTO obtenerMedicoPorId(@WebParam(name = "id") int id) {
        return medicoBo.obtenerPorId(id);
    }

    @WebMethod(operationName = "eliminarMedico")
    public int eliminarMedico(@WebParam(name = "id") int id) {
        return medicoBo.eliminar(id);
    }

    @WebMethod(operationName = "listarCitasProgramadas")
    public ArrayList<CitaDTO> listarCitasProgramadas(@WebParam(name = "idMedico") int idMedico) {
        return medicoBo.listarCitasProgramadas(idMedico);
    }
}
