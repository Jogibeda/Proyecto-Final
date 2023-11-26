import java.util.ArrayList;

public class Encuesta {
    private String nombre;
    private int numPreguntas;
    private ArrayList<Pregunta> preguntas;

    public Encuesta(String nombre) {
        this.nombre = nombre;
        this.preguntas = new ArrayList<>();
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    public void agregarNumPreguntas(int numPreguntas) {
        this.numPreguntas = numPreguntas;
    }

    public void mostrarPreguntas() {
        System.out.println("Preguntas disponibles:");
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println((i + 1) + ". " + preguntas.get(i).getPregunta());
        }
    }

    public Pregunta obtenerPregunta(int indice) {
        return preguntas.get(indice);
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
