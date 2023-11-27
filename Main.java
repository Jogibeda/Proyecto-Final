import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AlmacenEncuestas almacenEncuestas = AlmacenEncuestas.obtenerInstancia();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("¿Qué acción desea realizar?");
            System.out.println("1. Crear nueva encuesta");
            System.out.println("2. Responder Encuesta");
            System.out.println("3. Mostrar informe de encuesta");
            System.out.println("0. Salir");
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
                        if (terminarEncuesta.equals("N") || terminarEncuesta.equals("n")) {
                            terminar = true;
                        }
                        numPreguntas = numPreguntas + 1;
                    }
                    encuesta.agregarNumPreguntas(numPreguntas);
                    almacenEncuestas.agregarEncuesta(encuesta);
                    break;
                case 2:
                    try {
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
                            String respuesta = encuestaSeleccionada.obtenerPregunta(i).getOpciones().get(respuestaIndex - 1);
                            encuestaSeleccionada.obtenerPregunta(i).setRespuesta(respuesta);
                        }
                        break;
                    } catch (NullPointerException e) {
                        System.out.println("No pongas valores nulos " + e);

                    }
                    // Responder Encuesta
                case 3:
                    scanner.nextLine();
                    System.out.println("Eliga una encuesta para recibir el Informe");
                    System.out.println("Encuestas disponibles: ");
                    System.out.println(almacenEncuestas.obtenerNombreEncuestas());

                    String nombreEncuestaElegida = scanner.nextLine();
                    Encuesta encuestaSeleccionada = almacenEncuestas.obtenerEncuesta(nombreEncuestaElegida);

                    System.out.println();
                    System.out.println("-----------------------------------------------------");
                    System.out.println("INFORME DE LA ENCUESTA: "+encuestaSeleccionada.getNombre());
                    for (int i = 0; i < encuestaSeleccionada.getNumPreguntas(); i++) {
                        System.out.println("Pregunta "+(i+1)+". "+encuestaSeleccionada.obtenerPregunta(i).getPregunta());
                        System.out.println("Opciones disponibles:");
                        for (int j = 0; j < encuestaSeleccionada.obtenerPregunta(i).getOpciones().size(); j++) {
                            System.out.println((j + 1) + ". " + encuestaSeleccionada.obtenerPregunta(i).getOpciones().get(j));
                        }
                        System.out.println("Tu Respuesta Fue: ");
                        System.out.println(encuestaSeleccionada.obtenerPregunta(i).getRespuesta());
                        System.out.println();
                    }
                    System.out.println("-----------------------------------------------------");
                    System.out.println();


                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.\n");

            }

        }
    }
}
