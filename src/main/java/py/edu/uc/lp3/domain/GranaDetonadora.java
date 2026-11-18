package py.edu.uc.lp3.domain;

import py.edu.uc.lp3.service.DominioValidador;

public class GranaDetonadora extends Granada {

    public GranaDetonadora(String equipo) {
        this(60, 400f, equipo, 0.45f, 18f, 20f, 2.0f, 100f);
    }

    /** Valida después de super() (ver DominioValidador): no es lógica de la Entity, solo evita construir con datos ilegales. */
    public GranaDetonadora(int danio, float precio, String equipo, float peso,
                           float radioExplosion, float distanciaLanzamiento,
                           float aturdimiento, float visibilidad) {
        super(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, aturdimiento, visibilidad);
        DominioValidador.validarComunes(danio, precio, equipo, peso);
        DominioValidador.validarGranada(radioExplosion, distanciaLanzamiento, aturdimiento, visibilidad);
    }

    @Override
    public String getComportamiento() {
        return "Explota con dano " + getDanio() + " en radio " + getRadioExplosion() + "m, aturdiendo " + getAturdimiento() + "s y causando visibilidad " + getVisibilidad() + ".";
    }
}
