package py.edu.uc.lp3.LT_taller_git_2026.cs2;

public class GranaFlash extends Granada {
    protected float ceguera;

    public GranaFlash() {
    }

    public GranaFlash(int danio, float precio, String equipo, float peso,
                      float radioExplosion, float distanciaLanzamiento,
                      float ceguera, float visibilidad) {
        super(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, ceguera, visibilidad);
        this.ceguera = ceguera;
    }

    @Override
    public String getComportamiento() {
        return "Destella con intensidad " + ceguera + " a " + distanciaLanzamiento + "m, cegando a enemigos en radio " + radioExplosion + "m.";
    }

    public float getCeguera() {
        return ceguera;
    }

    public void setCeguera(float ceguera) {
        this.ceguera = ceguera;
    }
}
