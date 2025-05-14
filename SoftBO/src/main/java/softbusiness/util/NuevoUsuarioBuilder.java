package softbusiness.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import softmodel.modelos.CuentaDTO;
import softmodel.modelos.PersonaDTO;
import softmodel.util.Genero;
import org.json.JSONObject;

public class NuevoUsuarioBuilder {

    private String dni;
    private String contrasenha;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String numCelular;
    private Genero genero;

    public NuevoUsuarioBuilder(String dni, String contrasenha) {
        this.dni = dni;
        this.contrasenha = contrasenha;
        this.correoElectronico = null;
        this.numCelular = null;
        this.genero = null;

        try {
            String urlStr = "https://www.facturaloconlwp.com/api/persona/dni/" + dni;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject json = new JSONObject(response.toString());

                if (json.has("firstname") && json.has("secondname")) {
                    this.nombres = json.optString("firstname", "");
                    String[] apellidos = json.optString("secondname", "").split(" ", 2);
                    this.apellidoPaterno = apellidos.length > 0 ? apellidos[0] : "";
                    this.apellidoMaterno = apellidos.length > 1 ? apellidos[1] : "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NuevoUsuarioBuilder conCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public NuevoUsuarioBuilder conNumCelular(String numCelular) {
        this.numCelular = numCelular;
        return this;
    }

    public NuevoUsuarioBuilder conGenero(Genero genero) {
        this.genero = genero;
        return this;
    }

    public PersonaDTO builNuevoUsuario() {
        PersonaDTO persona = new PersonaDTO();
        CuentaDTO cuenta = new CuentaDTO();
        cuenta.setContrasenha(this.contrasenha);
        cuenta.setNumeroDocumento(this.dni);
        persona.setCuenta(cuenta);
        persona.setCorreoElectronico(this.correoElectronico);
        persona.setNumCelular(this.numCelular);
        persona.setGenero(this.genero);
        persona.setNombres(this.nombres);
        persona.setApellido_paterno(this.apellidoPaterno);
        persona.setApellido_materno(this.apellidoMaterno);
        return persona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
}
