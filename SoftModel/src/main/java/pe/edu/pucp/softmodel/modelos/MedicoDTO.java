/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.softmodel.modelos;

/**
 *
 * @author Mcerv
 */
public class MedicoDTO extends PersonaDTO{
    private EspecialidadDTO especialidad;
    private Integer codMedico;

    /**
     * @return the especialidad
     */
    public EspecialidadDTO getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(EspecialidadDTO especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * @return the codMedico
     */
    public Integer getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(Integer codMedico) {
        this.codMedico = codMedico;
    }
    
}
