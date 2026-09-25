package py.edu.uc.lp3.LT_taller_git_2026.cs2;

public class GranaHumo extends Granada {
    protected float duracionHumo;

    public GranaHumo() {
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

    public void setDuracionHumo(float duracionHumo) {
        this.duracionHumo = duracionHumo;
    }
}
