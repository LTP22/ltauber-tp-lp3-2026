package py.edu.uc.lp3.domain;

import py.edu.uc.lp3.service.DominioValidador;

public class GranaFlash extends Granada {
    protected float ceguera;

    public GranaFlash(String equipo) {
        this(0, 200f, equipo, 0.3f, 25f, 15f, 3.0f, 100f);
    }

    /** Valida después de super() (ver DominioValidador): no es lógica de la Entity, solo evita construir con datos ilegales. */
    public GranaFlash(int danio, float precio, String equipo, float peso,
                      float radioExplosion, float distanciaLanzamiento,
                      float ceguera, float visibilidad) {
        super(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, ceguera, visibilidad);
        DominioValidador.validarComunes(danio, precio, equipo, peso);
        DominioValidador.validarGranada(radioExplosion, distanciaLanzamiento, ceguera, visibilidad);
        this.ceguera = ceguera;
    }

    @Override
    public String getComportamiento() {
        return "Destella con intensidad " + ceguera + " a " + getDistanciaLanzamiento() + "m, cegando a enemigos en radio " + getRadioExplosion() + "m.";
    }

    public float getCeguera() {
        return ceguera;
    }
}
