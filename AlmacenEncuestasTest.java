import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AlmacenEncuestasTest {

    @Test
    public void testAgregarEncuesta() {
        AlmacenEncuestas almacenEncuestas = AlmacenEncuestas.obtenerInstancia();
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");

        almacenEncuestas.agregarEncuesta(encuesta);

        assertNotNull(almacenEncuestas.obtenerEncuesta("Encuesta de Ejemplo"));
    }

    @Test
    public void testObtenerNombreEncuestas() {
        AlmacenEncuestas almacenEncuestas = AlmacenEncuestas.obtenerInstancia();
        Encuesta encuesta1 = new Encuesta("Encuesta de opinión");

        almacenEncuestas.agregarEncuesta(encuesta1);

        ArrayList nombres = almacenEncuestas.obtenerEncuestas();
        assertTrue(nombres.contains("Encuesta de opinión"));
    }

    @Test
    public void testObtenerEncuesta() {
        AlmacenEncuestas almacenEncuestas = AlmacenEncuestas.obtenerInstancia();
        Encuesta encuesta = new Encuesta("Encuesta de Ejemplo");

        almacenEncuestas.agregarEncuesta(encuesta);

        assertEquals(encuesta, almacenEncuestas.obtenerEncuesta("Encuesta de Ejemplo"));
    }

    @Test
    public void testObtenerInstanciaSingleton() {
        AlmacenEncuestas instancia1 = AlmacenEncuestas.obtenerInstancia();
        AlmacenEncuestas instancia2 = AlmacenEncuestas.obtenerInstancia();

        assertSame(instancia1, instancia2);
    }
}
