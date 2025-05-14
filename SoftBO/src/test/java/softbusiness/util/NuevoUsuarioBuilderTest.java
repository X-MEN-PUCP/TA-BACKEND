/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package softbusiness.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import softmodel.modelos.PersonaDTO;

public class NuevoUsuarioBuilderTest {

    @Test
    public void testConsultaDesdeApi_facturalo() {
        String dni = "04412417";
        String contrasenha = "123456";

        NuevoUsuarioBuilder builder = new NuevoUsuarioBuilder(dni, contrasenha);
        PersonaDTO persona = builder.builNuevoUsuario();

        assertNotNull(persona.getNombres());
        assertFalse(persona.getNombres().isBlank());

        assertNotNull(persona.getApellido_paterno());
        assertFalse(persona.getApellido_paterno().isBlank());

        assertNotNull(persona.getApellido_materno());
        assertFalse(persona.getApellido_materno().isBlank());

        System.out.println("Nombre completo: " + persona.getNombres() + " " +
            persona.getApellido_paterno() + " " + persona.getApellido_materno());
    }
}