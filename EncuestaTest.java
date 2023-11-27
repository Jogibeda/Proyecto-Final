import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


public class EncuestaTest {
    private final ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;

    @Before
    public void configurarSalidaConsola() {
        System.setOut(new PrintStream(salidaConsola));
    }

    @After
    public void restablecerSystemOut() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testMostrarPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");
        PreguntaAbiertaFactory preguntaAbiertaFactory = new PreguntaAbiertaFactory("¿Cuál es tu color favorito?");
        PreguntaOpcionMultipleFactory preguntaOpcionMultipleFactory = new PreguntaOpcionMultipleFactory(
                "¿Cuál es tu película favorita?", new ArrayList<>()
        );

        Pregunta preguntaAbierta = preguntaAbiertaFactory.crearPregunta();
        Pregunta preguntaOpcionMultiple = preguntaOpcionMultipleFactory.crearPregunta();

        encuesta.agregarPregunta(preguntaAbierta);
        encuesta.agregarPregunta(preguntaOpcionMultiple);

        encuesta.mostrarPreguntas();

        // Capturamos la salida del sistema
        String salidaCapturada = salidaConsola.toString().trim();

        // Verificamos que la salida contiene las preguntas
        assertTrue(salidaCapturada.contains("1. " + preguntaAbierta.getPregunta()));
        assertTrue(salidaCapturada.contains("2. " + preguntaOpcionMultiple.getPregunta()));
    }

    @Test
    public void testAgregarPregunta() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");
        PreguntaAbiertaFactory preguntaAbiertaFactory = new PreguntaAbiertaFactory("¿Cuál es tu color favorito?");
        Pregunta preguntaAbierta = preguntaAbiertaFactory.crearPregunta();

        encuesta.agregarPregunta(preguntaAbierta);

        assertEquals(1, encuesta.getNumPreguntas());
    }

    @Test
    public void testAgregarNumPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");

        encuesta.agregarNumPreguntas(5);

        assertEquals(5, encuesta.getNumPreguntas());
    }

    @Test
    public void testObtenerPregunta() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");
        PreguntaAbiertaFactory preguntaAbiertaFactory = new PreguntaAbiertaFactory("¿Cuál es tu color favorito?");
        PreguntaOpcionMultipleFactory preguntaOpcionMultipleFactory = new PreguntaOpcionMultipleFactory(
                "¿Cuál es tu película favorita?", new ArrayList<>()
        );

        Pregunta preguntaAbierta = preguntaAbiertaFactory.crearPregunta();
        Pregunta preguntaOpcionMultiple = preguntaOpcionMultipleFactory.crearPregunta();

        encuesta.agregarPregunta(preguntaAbierta);
        encuesta.agregarPregunta(preguntaOpcionMultiple);

        assertEquals(preguntaAbierta, encuesta.obtenerPregunta(0));
        assertEquals(preguntaOpcionMultiple, encuesta.obtenerPregunta(1));
    }

    @Test
    public void testGetNombre() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");

        assertEquals("Encuesta de Ejemplo", encuesta.getNombre());
    }

    @Test
    public void testGetNumPreguntas() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");

        assertEquals(0, encuesta.getNumPreguntas());
    }

    @Test
    public void testSetNombre() {
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");

        encuesta.setNombre("Nueva Encuesta");

        assertEquals("Nueva Encuesta", encuesta.getNombre());
    }
}
