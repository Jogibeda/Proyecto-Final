import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class PreguntaFactoryTest {

    @Test
    public void testPreguntaAbiertaFactoryCrearPregunta() {
        String pregunta = "¿Cuál es tu color favorito?";
        PreguntaFactory preguntaAbiertaFactory = new PreguntaAbiertaFactory(pregunta);

        Pregunta preguntaAbierta = preguntaAbiertaFactory.crearPregunta();

        assertNotNull(preguntaAbierta);
        assertTrue(preguntaAbierta instanceof PreguntaAbierta);
        assertEquals(pregunta, preguntaAbierta.getPregunta());
        
        // La respuesta debería ser nula al principio
        assertNull(preguntaAbierta.getRespuesta()); 
    }

    @Test
    public void testPreguntaOpcionMultipleFactoryCrearPregunta() {
        String pregunta = "¿Cuál es tu color favorito?";
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Rojo");
        opciones.add("Azul");
        opciones.add("Verde");

        PreguntaFactory preguntaOpcionMultipleFactory = new PreguntaOpcionMultipleFactory(pregunta, opciones);

        Pregunta preguntaOpcionMultiple = preguntaOpcionMultipleFactory.crearPregunta();

        assertNotNull(preguntaOpcionMultiple);
        assertTrue(preguntaOpcionMultiple instanceof PreguntaOpcionMultiple);
        assertEquals(pregunta, preguntaOpcionMultiple.getPregunta());
        assertEquals(opciones, ((PreguntaOpcionMultiple) preguntaOpcionMultiple).getOpciones());
        
        // La respuesta debería ser nula al principio
        assertNull(preguntaOpcionMultiple.getRespuesta()); 
    }
}

