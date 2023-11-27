import java.util.ArrayList;

// Creamos Interfaz de la Pregunta
interface Pregunta {
    String getPregunta();
    String getRespuesta();
    ArrayList<String> getOpciones();
    String setRespuesta(String respuesta);
    String setTipoPregunta(String tipoPregunta);
    String getTipoPregunta();
}

// Implementamos Preguntas Concretas
class PreguntaAbierta implements Pregunta {
    private String pregunta;
    private String respuesta;
    private String tipoPregunta;

    public PreguntaAbierta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String getPregunta() {
        return pregunta;
    }

    @Override
    public String getRespuesta() {
        return respuesta;
    }

    @Override
    public ArrayList<String> getOpciones() {return null; } //No tiene opciones de Respuesta

    @Override
    public String setRespuesta(String respuesta) {
        this.respuesta = respuesta;
        return respuesta;
    }

    @Override
    public String setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
        return tipoPregunta;
    }

    @Override
    public String getTipoPregunta() { return tipoPregunta; }
}

class PreguntaOpcionMultiple implements Pregunta {
    private String pregunta;
    private ArrayList<String> opciones;
    private String respuesta;
    private String tipoPregunta;

    public PreguntaOpcionMultiple(String pregunta, ArrayList<String> opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public String getPregunta() {
        return pregunta;
    }

    @Override
    public ArrayList<String> getOpciones() {
        return opciones;
    }

    @Override
    public String setRespuesta(String respuesta) {
        this.respuesta = respuesta;
        return respuesta;
    }

    @Override
    public String getRespuesta() {
        return respuesta;
    }

    @Override
    public String setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
        return tipoPregunta;
    }

    public String getTipoPregunta() { return tipoPregunta; }
}