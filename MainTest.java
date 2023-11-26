import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test //Crear una instancia de Encuesta correctamente
    public void testEncuestaCreation() {
    
        ArrayList<String> opciones = new ArrayList<>(List.of("Lunes", "Jueves", "Viernes"));
        Encuesta encuesta = new Encuesta("¿Qué día prefieres el examen?", opciones);

        assertEquals("¿Qué día prefieres el examen?", encuesta.getPregunta());
        assertEquals(opciones, encuesta.getOpciones());
    }

    @Test //Crear una instancia de Pregunta Abierta correctamente
    public void testPreguntaAbiertaCreation() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cuál es tu comida favorita?");

        assertEquals("¿Cuál es tu comida favorita?", preguntaAbierta.getPregunta());
        assertNull(preguntaAbierta.getOpciones());
    }

    @Test //Agregar una Encuesta al Encuestador
    public void testAgregarEncuesta() {
        Encuestador encuestador = new Encuestador();
        Encuesta encuesta = new Encuesta("¿Cuántos años tienes?", new ArrayList<>());

        encuestador.agregarEncuesta(encuesta);

        assertEquals(encuesta, encuestador.obtenerEncuesta(0));
    }

    @Test // mostrarEncuestas() muestra las preguntas 
    public void testMostrarEncuestas() {
        Encuestador encuestador = new Encuestador();
        Encuesta encuesta1 = new Encuesta("¿Cuál es tu animal favorito?", new ArrayList<>());
        Encuesta encuesta2 = new Encuesta("¿Qué tipo de música te gusta?", new ArrayList<>());

        encuestador.agregarEncuesta(encuesta1);
        encuestador.agregarEncuesta(encuesta2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        encuestador.mostrarEncuestas();

        String expectedOutput = "Encuestas disponibles:" + System.lineSeparator() +
                        "1. ¿Cuál es tu animal favorito?" + System.lineSeparator() +
                        "2. ¿Qué tipo de música te gusta?" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test //respuestas encuesta
    public void testResponderEncuesta() {
        Encuestado encuestado = new Encuestado();
        String pregunta = "¿Cuál es tu animal favorito?";
        String respuesta = "Caballo";

        encuestado.responderEncuesta(pregunta, respuesta);

        assertEquals(1, encuestado.getRespuestas().size());
        assertEquals(pregunta, encuestado.getRespuestas().get(0)[0]);
        assertEquals(respuesta, encuestado.getRespuestas().get(0)[1]);
    }

    @Test //mostrarRespuestas() muestra las respuestas correctamente
    public void testMostrarRespuestas() {
        Encuestado encuestado = new Encuestado();
        encuestado.responderEncuesta("¿Cuánto es 2+2?", "4");
        encuestado.responderEncuesta("¿Tienes sueño?", "si");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        encuestado.mostrarRespuestas();

        String expectedOutput = "Respuestas recopiladas:" + System.lineSeparator() +
                        "Pregunta: ¿Cuánto es 2+2?" + System.lineSeparator() +
                        "Respuesta: 4" + System.lineSeparator() + "-----" + System.lineSeparator() +
                        "Pregunta: ¿Tienes sueño?" + System.lineSeparator() +
                        "Respuesta: si" + System.lineSeparator() + "-----"+ System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
    }

