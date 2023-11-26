import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class Encuesta {
//    private String pregunta;
//    private ArrayList<String> opciones;
//
//    public Encuesta(String pregunta, ArrayList<String> opciones) {
//        this.pregunta = pregunta;
//        this.opciones = opciones;
//    }
//
//    public String getPregunta() {
//        return pregunta;
//    }
//
//    public ArrayList<String> getOpciones() {
//        return opciones;
//    }
//}

//class PreguntaAbierta extends Encuesta {
//    public PreguntaAbierta(String pregunta) {
//        super(pregunta, new ArrayList<>());
//    }
//
//    @Override
//    public ArrayList<String> getOpciones() {
//        return null; // Las preguntas abiertas no tienen opciones
//    }
//}

class Encuesta {
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

    public void agregarNumPreguntas(int numPreguntas) { this.numPreguntas = numPreguntas; }

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

    public int getNumPreguntas() { return numPreguntas; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

// Clase para gestionar el almacenamiento de encuestas (utilizando Singleton)
class AlmacenEncuestas {
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

    public String obtenerNombreEncuestas() {
        String nombre = null;
        for (Encuesta encuesta : encuestas) {
            nombre = encuesta.getNombre();
            return nombre;
        }
        return null;
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
        Encuestado encuestado = new Encuestado();
        AlmacenEncuestas almacenEncuestas = AlmacenEncuestas.obtenerInstancia();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("¿Qué acción desea realizar?");
            System.out.println("1. Crear nueva encuesta");
            System.out.println("2. Responder Encuesta");
            System.out.println("3. Mostrar informe de encuesta");
            System.out.println("0. Salir");

            // System.out.println("1. Crear nueva pregunta de opción múltiple");
            // System.out.println("2. Crear nueva pregunta abierta");
            // System.out.println("3. Responder pregunta");
            // System.out.println("4. Mostrar respuestas recopiladas");
            // System.out.println("0. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                case 1:
                    // Crear nueva encuesta
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                    System.out.println("Ingrese el titulo de la encuesta");
                    String nombreEncuesta = scanner.nextLine();
                    Encuesta encuesta = new Encuesta(nombreEncuesta);
                    System.out.println("Agrega las preguntas a tu Encuesta");
                    boolean terminar = false;
                    int numPreguntas = 0;
                    while (!terminar) {
                        System.out.println("¿Qué tipo de pregunta quiere agregar?");
                        System.out.println("1. Opción Multiple");
                        System.out.println("2. Abierta");
                        int tipoPregunta = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ingrese la Pregunta:");
                        String preguntaIngresada = scanner.nextLine();
                        if (tipoPregunta == 1) {
                            System.out.println("Ingrese las opciones separadas por comas:");
                            String opcionesInput = scanner.nextLine();
                            ArrayList<String> opciones = new ArrayList<>(List.of(opcionesInput.split(",")));
                            PreguntaFactory factory = new PreguntaOpcionMultipleFactory(preguntaIngresada, opciones);
                            Pregunta pregunta = factory.crearPregunta();
                            System.out.println("Pregunta realizada exitosamente!");
                            encuesta.agregarPregunta(pregunta);
                        } else {
                            PreguntaFactory factory = new PreguntaAbiertaFactory(preguntaIngresada);
                            Pregunta pregunta = factory.crearPregunta();
                            System.out.println("Pregunta realizada exitosamente!");
                            encuesta.agregarPregunta(pregunta);
                        }
                        System.out.println("Quieres agregar otra pregunta? S/N");
                        String terminarEncuesta = scanner.nextLine();
                        if (terminarEncuesta.equals("N")) {
                            terminar = true;
                        }
                        numPreguntas = numPreguntas + 1;
                    }
                    encuesta.agregarNumPreguntas(numPreguntas);
                    almacenEncuestas.agregarEncuesta(encuesta);

                    // String preguntaOpcionMultiple = scanner.nextLine();
                    // System.out.println("Ingrese las opciones separadas por comas:");
                    // String opcionesInput = scanner.nextLine();
                    // ArrayList<String> opciones = new
                    // ArrayList<>(List.of(opcionesInput.split(",")));
                    // Encuesta nuevaEncuestaOpcionMultiple = new Encuesta(preguntaOpcionMultiple,
                    // opciones);
                    // encuestador.agregarEncuesta(nuevaEncuestaOpcionMultiple);
                    // System.out.println("Encuesta de opción múltiple creada exitosamente.\n");
                    break;
                case 2:
                    // Responder Encuesta
                    scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                    System.out.println("Elige la encuesta a responder");
                    System.out.println("Encuestas disponibles: ");
                    System.out.println(almacenEncuestas.obtenerNombreEncuestas());

                    String nombreEncuestaElegida = scanner.nextLine();
                    Encuesta encuestaSeleccionada = almacenEncuestas.obtenerEncuesta(nombreEncuestaElegida);
                    for (int i = 0; i < encuestaSeleccionada.getNumPreguntas(); i++) {
                        System.out.println(encuestaSeleccionada.obtenerPregunta(i).getPregunta());
                        System.out.println("Opciones disponibles:");
                        for (int j = 0; j < encuestaSeleccionada.obtenerPregunta(i).getOpciones().size(); j++) {
                            System.out.println((j + 1) + ". " + encuestaSeleccionada.obtenerPregunta(i).getOpciones().get(j));
                        }
                        System.out.println("Elige una Opción:");
                        int respuestaIndex = scanner.nextInt();
                        String respuesta = encuestaSeleccionada.obtenerPregunta(i).getOpciones().get(respuestaIndex-1);
                        encuestaSeleccionada.obtenerPregunta(i).setRespuesta(respuesta);
//                        encuestaSeleccionada.obtenerPregunta(i).setRespuesta(respuesta);
                    }

                    // System.out.println("Ingrese la pregunta de la encuesta abierta:");
                    // String preguntaAbierta = scanner.nextLine();
                    // Encuesta nuevaEncuestaAbierta = new PreguntaAbierta(preguntaAbierta);
                    // encuestador.agregarEncuesta(nuevaEncuestaAbierta);
                    // System.out.println("Encuesta abierta creada exitosamente.\n");
                    break;
                // case 3:
                // // Responder encuesta
                // encuestador.mostrarEncuestas();
                // System.out.println("Seleccione una encuesta para responder:");
                // int indiceEncuesta = scanner.nextInt();
                // Encuesta encuestaSeleccionada = encuestador.obtenerEncuesta(indiceEncuesta -
                // 1);
                //
                // System.out.println(encuestaSeleccionada.getPregunta());
                // try {
                // if (encuestaSeleccionada.getOpciones() != null) {
                // // Encuesta de opción múltiple
                // for (int i = 0; i < encuestaSeleccionada.getOpciones().size(); i++) {
                // System.out.println((i + 1) + ". " +
                // encuestaSeleccionada.getOpciones().get(i));
                // }
                //
                // System.out.println(
                // "Ingrese su respuesta (1-" + encuestaSeleccionada.getOpciones().size() + "):
                // ");
                // int respuestaOpcionMultiple = scanner.nextInt();
                //
                // if (respuestaOpcionMultiple > 0
                // && respuestaOpcionMultiple <= encuestaSeleccionada.getOpciones().size()) {
                // encuestado.responderEncuesta(encuestaSeleccionada.getPregunta(),
                // encuestaSeleccionada.getOpciones().get(respuestaOpcionMultiple - 1));
                // System.out.println("¡Respuesta registrada!\n");
                // } else {
                // System.out.println("Opción no válida. Inténtelo de nuevo.\n");
                // }
                // } else {
                // // Encuesta abierta
                // scanner.nextLine(); // Consumir la nueva línea después de nextInt()
                // System.out.println("Ingrese su respuesta abierta:");
                // String respuestaAbierta = scanner.nextLine();
                // encuestado.responderEncuesta(encuestaSeleccionada.getPregunta(),
                // respuestaAbierta);
                // System.out.println("¡Respuesta registrada!\n");
                // }
                // } catch (IOError error) {
                // System.out.println("Hay un error en la informacion proporcionada= " + error);
                // }
                // break;
                // case 4:
                // // Mostrar respuestas recopiladas
                // encuestado.mostrarRespuestas();
                // break;
                case 3:
                    System.out.println("Encuestas disponibles: ");
                    System.out.println(almacenEncuestas.obtenerNombreEncuestas());
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.\n");

            }

        }
    }
}
