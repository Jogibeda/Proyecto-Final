import java.io.IOError;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Encuesta {
    private String pregunta;
    private ArrayList<String> opciones;

    public Encuesta(String pregunta, ArrayList<String> opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public String getPregunta() {
        return pregunta;
    }

    public ArrayList<String> getOpciones() {
        return opciones;
    }
}

class PreguntaAbierta extends Encuesta {
    public PreguntaAbierta(String pregunta) {
        super(pregunta, new ArrayList<>());
    }

    @Override
    public ArrayList<String> getOpciones() {
        return null; // Las preguntas abiertas no tienen opciones
    }
}

class Encuestador {
    private ArrayList<Encuesta> encuestas;

    public Encuestador() {
        this.encuestas = new ArrayList<>();
    }

    public void agregarEncuesta(Encuesta encuesta) {
        encuestas.add(encuesta);
    }

    public void mostrarEncuestas() {
        System.out.println("Encuestas disponibles:");
        for (int i = 0; i < encuestas.size(); i++) {
            System.out.println((i + 1) + ". " + encuestas.get(i).getPregunta());
        }
    }

    public Encuesta obtenerEncuesta(int indice) {
        return encuestas.get(indice);
    }
}

class Encuestado {
    private ArrayList<String[]> respuestas;

    public Encuestado() {
        this.respuestas = new ArrayList<>();
    }

    public void responderEncuesta(String pregunta, String respuesta) {
        String[] respuestaEncuesta = { pregunta, respuesta };
        respuestas.add(respuestaEncuesta);
    }

    public void mostrarRespuestas() {
        System.out.println("Respuestas recopiladas:");
        for (String[] respuesta : respuestas) {
            System.out.println("Pregunta: " + respuesta[0]);
            System.out.println("Respuesta: " + respuesta[1]);
            System.out.println("-----");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Encuestador encuestador = new Encuestador();
        Encuestado encuestado = new Encuestado();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("¿Qué acción desea realizar?");
            System.out.println("1. Crear nueva encuesta de opción múltiple");
            System.out.println("2. Crear nueva encuesta abierta");
            System.out.println("3. Responder encuesta");
            System.out.println("4. Mostrar respuestas recopiladas");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                case 1:
                    // Crear nueva encuesta de opción múltiple
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                    System.out.println("Ingrese la pregunta de la encuesta:");
                    String preguntaOpcionMultiple = scanner.nextLine();
                    System.out.println("Ingrese las opciones separadas por comas:");
                    String opcionesInput = scanner.nextLine();
                    ArrayList<String> opciones = new ArrayList<>(List.of(opcionesInput.split(",")));
                    Encuesta nuevaEncuestaOpcionMultiple = new Encuesta(preguntaOpcionMultiple, opciones);
                    encuestador.agregarEncuesta(nuevaEncuestaOpcionMultiple);
                    System.out.println("Encuesta de opción múltiple creada exitosamente.\n");
                    break;
                case 2:
                    // Crear nueva encuesta abierta
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                    System.out.println("Ingrese la pregunta de la encuesta abierta:");
                    String preguntaAbierta = scanner.nextLine();
                    Encuesta nuevaEncuestaAbierta = new PreguntaAbierta(preguntaAbierta);
                    encuestador.agregarEncuesta(nuevaEncuestaAbierta);
                    System.out.println("Encuesta abierta creada exitosamente.\n");
                    break;
                case 3:
                    // Responder encuesta
                    encuestador.mostrarEncuestas();
                    System.out.println("Seleccione una encuesta para responder:");
                    int indiceEncuesta = scanner.nextInt();
                    Encuesta encuestaSeleccionada = encuestador.obtenerEncuesta(indiceEncuesta - 1);

                    System.out.println(encuestaSeleccionada.getPregunta());
                    try {
                        if (encuestaSeleccionada.getOpciones() != null) {
                            // Encuesta de opción múltiple
                            for (int i = 0; i < encuestaSeleccionada.getOpciones().size(); i++) {
                                System.out.println((i + 1) + ". " + encuestaSeleccionada.getOpciones().get(i));
                            }

                            System.out.println(
                                    "Ingrese su respuesta (1-" + encuestaSeleccionada.getOpciones().size() + "): ");
                            int respuestaOpcionMultiple = scanner.nextInt();

                            if (respuestaOpcionMultiple > 0
                                    && respuestaOpcionMultiple <= encuestaSeleccionada.getOpciones().size()) {
                                encuestado.responderEncuesta(encuestaSeleccionada.getPregunta(),
                                        encuestaSeleccionada.getOpciones().get(respuestaOpcionMultiple - 1));
                                System.out.println("¡Respuesta registrada!\n");
                            } else {
                                System.out.println("Opción no válida. Inténtelo de nuevo.\n");
                            }
                        } else {
                            // Encuesta abierta
                            scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                            System.out.println("Ingrese su respuesta abierta:");
                            String respuestaAbierta = scanner.nextLine();
                            encuestado.responderEncuesta(encuestaSeleccionada.getPregunta(), respuestaAbierta);
                            System.out.println("¡Respuesta registrada!\n");
                        }
                    } catch (IOError error) {
                        System.out.println("Hay un error en la informacion proporcionada= " + error);
                    }
                    break;
                case 4:
                    // Mostrar respuestas recopiladas
                    encuestado.mostrarRespuestas();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.\n");
            }
        }
    }
}
