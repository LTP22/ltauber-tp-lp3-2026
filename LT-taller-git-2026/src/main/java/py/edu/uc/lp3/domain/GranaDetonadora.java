package py.edu.uc.lp3.domain;

public class GranaDetonadora extends Granada {

    public GranaDetonadora(String equipo) {
        this(60, 400f, equipo, 0.45f, 18f, 20f, 2.0f, 100f);
    }

    public GranaDetonadora(int danio, float precio, String equipo, float peso,
                           float radioExplosion, float distanciaLanzamiento,
                           float aturdimiento, float visibilidad) {
        super(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, aturdimiento, visibilidad);
    }

    @Override
    public String getComportamiento() {
        return "Explota con dano " + danio + " en radio " + radioExplosion + "m, aturdiendo " + aturdimiento + "s y causando visibilidad " + visibilidad + ".";
    }
}
