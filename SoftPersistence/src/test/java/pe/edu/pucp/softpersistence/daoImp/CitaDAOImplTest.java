/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.softpersistence.daoImp;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softpersistence.dao.CitaDAO;
import pe.edu.pucp.softmodel.modelos.CitaDTO;

/**
 *
 * @author salva
 */
public class CitaDAOImplTest {

    private CitaDAO citadao;
    private ArrayList<CitaDTO> citas;

    public CitaDAOImplTest() {

        citadao = new CitaDAOImpl();

    }

    @Test
    public void testBuscarCitasDisponibles() {

        Integer idEspecialidad = 2;
        Integer codMedico = 1234;
        LocalDate fecha = LocalDate.of(2025, 6, 3);

        this.citas = citadao.buscarCitasDisponibles(idEspecialidad, null, null);
        if (citas.size() > 0) {
            System.out.println("Hay datos, son: " + citas.size() + " datos");
        } else {
            System.out.println("No hay datos");
        }

        for (CitaDTO cita : citas) {
            System.out.println("ID Cita: " + cita.getIdCita());
            System.out.println("Fecha: " + cita.getHorario().getFecha());
            System.out.println("Hora Inicio: " + cita.getHorario().getHoraInicio());
            System.out.println("ID Médico: " + cita.getMedico().getIdPersona());
            System.out.println("ID Especialidad: " + cita.getMedico().getEspecialidad().getIdEspecialidad());
            System.out.println("Estado: " + cita.getEstado());
            System.out.println("------------------------------");
        }

    }

}
