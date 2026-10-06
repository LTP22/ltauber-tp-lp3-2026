package py.edu.uc.lp3.domain;

public class GranaHumo extends Granada {
    protected float duracionHumo;

    public GranaHumo(String equipo) {
        this(0, 300f, equipo, 0.4f, 20f, 18f, 4.5f, 0f);
    }

    public GranaHumo(int danio, float precio, String equipo, float peso,
                     float radioExplosion, float distanciaLanzamiento,
                     float duracionHumo, float visibilidad) {
        super(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, duracionHumo, visibilidad);
        this.duracionHumo = duracionHumo;
    }

    @Override
    public String getComportamiento() {
        return "Crea cortina de humo durante " + duracionHumo + "s en radio " + radioExplosion + "m, ocultando visibilidad a " + distanciaLanzamiento + "m.";
    }

    public float getDuracionHumo() {
        return duracionHumo;
    }
}
