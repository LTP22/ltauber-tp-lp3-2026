package py.edu.uc.lp3.lt_taller_git_2026;

public class GranaDetonadora extends Granada {

    public GranaDetonadora() {
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
