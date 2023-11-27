
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class PreguntaTest {

    @Test
    public void testPreguntaAbiertaGetPregunta() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cuál es tu color favorito?");
        assertEquals("¿Cuál es tu color favorito?", preguntaAbierta.getPregunta());
    }

    @Test
    // getRespuesta devolverá null hasta que setRespuesta se utilice para establecer un valor específico
    public void testPreguntaAbiertaGetRespuesta() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cuál es tu color favorito?");
        assertNull(preguntaAbierta.getRespuesta());
    }

    @Test
    public void testPreguntaAbiertaGetOpciones() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cuál es tu color favorito?");
        assertNull(preguntaAbierta.getOpciones());
    }

    @Test
    public void testPreguntaAbiertaSetRespuesta() {
        PreguntaAbierta preguntaAbierta = new PreguntaAbierta("¿Cuál es tu color favorito?");
        preguntaAbierta.setRespuesta("Pizza");
        assertEquals("Pizza", preguntaAbierta.getRespuesta());
    }

    @Test
    public void testPreguntaOpcionMultipleGetPregunta() {
        PreguntaOpcionMultiple preguntaMultiple = new PreguntaOpcionMultiple("¿Cuál es tu color favorito?", null);
        assertEquals("¿Cuál es tu color favorito?", preguntaMultiple.getPregunta());
    }

    @Test
    public void testPreguntaOpcionMultipleGetOpciones() {
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Rojo");
        opciones.add("Azul");
        opciones.add("Verde");

        PreguntaOpcionMultiple preguntaMultiple = new PreguntaOpcionMultiple("¿Cuál es tu color favorito?", opciones);
        assertEquals(opciones, preguntaMultiple.getOpciones());
    }

    @Test
    // getRespuesta devolverá null hasta que setRespuesta se utilice para establecer un valor específico
    public void testPreguntaOpcionMultipleGetRespuesta() {
        PreguntaOpcionMultiple preguntaMultiple = new PreguntaOpcionMultiple("¿Cuál es tu color favorito?", null);
        assertNull(preguntaMultiple.getRespuesta());
    }

    @Test
    public void testPreguntaOpcionMultipleSetRespuesta() {
        PreguntaOpcionMultiple preguntaMultiple = new PreguntaOpcionMultiple("¿Cuál es tu color favorito?", null);
        preguntaMultiple.setRespuesta("Azul");
        assertEquals("Azul", preguntaMultiple.getRespuesta());
    }
}
