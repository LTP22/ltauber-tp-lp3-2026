package py.edu.uc.lp3.domain;

public abstract class Granada extends Arma {
    protected float radioExplosion;
    protected float distanciaLanzamiento;
    protected float aturdimiento;
    protected float visibilidad;

    protected Granada(int danio, float precio, String equipo, float peso,
                      float radioExplosion, float distanciaLanzamiento,
                      float aturdimiento, float visibilidad) {
        super(danio, precio, equipo, peso);
        if (radioExplosion < 0 || distanciaLanzamiento < 0) {
            throw new IllegalArgumentException("Radio y distancia no pueden ser negativos");
        }
        if (aturdimiento < 0 || visibilidad < 0) {
            throw new IllegalArgumentException("Aturdimiento y visibilidad no pueden ser negativos");
        }
        this.radioExplosion = radioExplosion;
        this.distanciaLanzamiento = distanciaLanzamiento;
        this.aturdimiento = aturdimiento;
        this.visibilidad = visibilidad;
    }

    @Override
    public String getComportamiento() {
        return "Se lanza a " + distanciaLanzamiento + "m y explota con radio " + radioExplosion + "m.";
    }

    public void lanzar() {
        System.out.println("Lanzando granada.");
    }

    public void explotar() {
        System.out.println("La granada exploto.");
    }

    public float getRadioExplosion() {
        return radioExplosion;
    }

    public float getDistanciaLanzamiento() {
        return distanciaLanzamiento;
    }

    public float getAturdimiento() {
        return aturdimiento;
    }

    public float getVisibilidad() {
        return visibilidad;
    }
}
