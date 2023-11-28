import java.util.ArrayList;

public interface EncuestaRepository {
    void agregarEncuesta(Encuesta encuesta);
    ArrayList<Encuesta> obtenerEncuestas();
    Encuesta obtenerEncuesta(String nombre);
}