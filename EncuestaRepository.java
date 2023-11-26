public interface EncuestaRepository {
    void agregarEncuesta(Encuesta encuesta);
    String obtenerNombreEncuestas();
    Encuesta obtenerEncuesta(String nombre);
}