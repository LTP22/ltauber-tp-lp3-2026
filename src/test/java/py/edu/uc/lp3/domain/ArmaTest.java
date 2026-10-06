package py.edu.uc.lp3.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmaTest {

    @Test
    void constructorSimpleDejaArmaEnEstadoLegal() {
        Pistola pistola = new Pistola("T");

        assertEquals(12, pistola.getBalasEnCargador());
        assertEquals(25, pistola.getDanio());
        assertTrue(pistola.tieneBalas());
    }

    @Test
    void constructorCompletoRechazaDanioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Pistola(-1, 500f, "T", 1.5f, 0.75f, 12, 3, 0.3f, 0.5f, "pistol_fire"));
    }

    @Test
    void constructorRechazaEquipoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Rifle("X"));
    }

    @Test
    void disparoSinDistanciaConsumeBala() {
        Pistola pistola = new Pistola("T");

        int danio = pistola.disparar();

        assertEquals(11, pistola.getBalasEnCargador());
        assertEquals(Math.round(25 * 0.75f), danio);
    }

    @Test
    void disparoConDistanciaHaceMenosDanioQueSinDistancia() {
        Pistola cercana = new Pistola("T");
        Pistola lejana = new Pistola("T");

        assertTrue(lejana.disparar(50f) < cercana.disparar());
    }

    @Test
    void disparoRechazaDistanciaNegativa() {
        Pistola pistola = new Pistola("T");

        assertThrows(IllegalArgumentException.class, () -> pistola.disparar(-5f));
    }

    @Test
    void armaSinBalasNoDispara() {
        Pistola pistola = new Pistola("T");
        for (int i = 0; i < 12; i++) {
            pistola.disparar();
        }

        assertFalse(pistola.tieneBalas());
        assertEquals(0, pistola.disparar());
    }

    @Test
    void recargarRestauraBalasYConsumeUnCargador() {
        Pistola pistola = new Pistola("T");
        pistola.disparar();

        assertTrue(pistola.recargar());
        assertEquals(12, pistola.getBalasEnCargador());
        assertEquals(2, pistola.getCargadores());
    }

    @Test
    void recargarConCargadorLlenoNoHaceNada() {
        Pistola pistola = new Pistola("T");

        assertFalse(pistola.recargar());
        assertEquals(3, pistola.getCargadores());
    }

    @Test
    void granadaSeLanzaUnaSolaVez() {
        GranaFlash granada = new GranaFlash("T");

        assertTrue(granada.lanzar());
        assertFalse(granada.lanzar());
        assertTrue(granada.isLanzada());
    }

    @Test
    void comportamientoSeSobreescribePorClaseHija() {
        Arma pistola = new Pistola("T");
        Arma granada = new GranaHumo("T");

        assertTrue(pistola.getComportamiento().startsWith("Dispara con precision"));
        assertTrue(granada.getComportamiento().startsWith("Crea cortina de humo"));
    }
}
