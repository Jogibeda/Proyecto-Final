import java.util.ArrayList;

// Creamos la Interfaz Factory
interface PreguntaFactory {
    Pregunta crearPregunta();
}

// Implementar Factories Concretos
class PreguntaAbiertaFactory implements PreguntaFactory {
    private String pregunta;

    public PreguntaAbiertaFactory(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public Pregunta crearPregunta() {
        return new PreguntaAbierta(pregunta);
    }
}

class PreguntaOpcionMultipleFactory implements PreguntaFactory {
    private String pregunta;
    private ArrayList<String> opciones;

    public PreguntaOpcionMultipleFactory(String pregunta, ArrayList<String> opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    @Override
    public Pregunta crearPregunta() {
        return new PreguntaOpcionMultiple(pregunta, opciones);
    }
}