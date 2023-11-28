import java.util.ArrayList;

// Clase para gestionar el almacenamiento de encuestas (utilizando Singleton y Repository)
public class AlmacenEncuestas implements EncuestaRepository {
    private static AlmacenEncuestas instancia;
    private ArrayList<Encuesta> encuestas;

    private AlmacenEncuestas() {
        encuestas = new ArrayList<>();
    }

    public static synchronized AlmacenEncuestas obtenerInstancia() {
        if (instancia == null) {
            instancia = new AlmacenEncuestas();
        }
        return instancia;
    }

    public void agregarEncuesta(Encuesta encuesta) {
        this.encuestas.add(encuesta);
    }

    public ArrayList<Encuesta> obtenerEncuestas() {
        return this.encuestas;
    }

    public Encuesta obtenerEncuesta(String nombre) {
        for (Encuesta encuesta : encuestas) {
            if (encuesta.getNombre().equals(nombre)) {
                return encuesta;
            }
        }
        return null;
    }

}
