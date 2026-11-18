package py.edu.uc.lp3.domain;

import py.edu.uc.lp3.service.DominioValidador;

public class Rifle extends ArmaDeFuego {

    public Rifle(String equipo) {
        this(77, 2100f, equipo, 3.6f, 0.85f, 30, 5, 0.75f, 2.0f, "rifle_fire");
    }

    /** Valida después de super() (ver DominioValidador): no es lógica de la Entity, solo evita construir con datos ilegales. */
    public Rifle(int danio, float precio, String equipo, float peso,
                 float precision, int balasCargador, int cargadores,
                 float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
        DominioValidador.validarComunes(danio, precio, equipo, peso);
        DominioValidador.validarArmaDeFuego(precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    public void rafagaAutomatica() {
        System.out.println("Rafaga automatica con rifle.");
    }
}
