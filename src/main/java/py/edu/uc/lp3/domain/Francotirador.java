package py.edu.uc.lp3.domain;

import py.edu.uc.lp3.service.DominioValidador;

public class Francotirador extends ArmaDeFuego {

    public Francotirador(String equipo) {
        this(115, 4750f, equipo, 4.9f, 0.95f, 10, 4, 1.2f, 3.3f, "sniper_fire");
    }

    /** Valida después de super() (ver DominioValidador): no es lógica de la Entity, solo evita construir con datos ilegales. */
    public Francotirador(int danio, float precio, String equipo, float peso,
                         float precision, int balasCargador, int cargadores,
                         float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
        DominioValidador.validarComunes(danio, precio, equipo, peso);
        DominioValidador.validarArmaDeFuego(precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    public void disparoSinRuido() {
        System.out.println("Disparo silencioso con francotirador.");
    }
}
