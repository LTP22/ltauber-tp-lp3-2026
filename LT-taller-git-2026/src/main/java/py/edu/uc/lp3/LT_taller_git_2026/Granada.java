package py.edu.uc.lp3.herencia;

public class Granada extends Arma {
    protected float radioExplosion;
    protected float distanciaLanzamiento;
    protected float aturdimiento;
    protected float visibilidad;

    public Granada() {
    }

    public Granada(int danio, float precio, String equipo, float peso,
                   float radioExplosion, float distanciaLanzamiento,
                   float aturdimiento, float visibilidad) {
        super(danio, precio, equipo, peso);
        this.radioExplosion = radioExplosion;
        this.distanciaLanzamiento = distanciaLanzamiento;
        this.aturdimiento = aturdimiento;
        this.visibilidad = visibilidad;
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

    public void setRadioExplosion(float radioExplosion) {
        this.radioExplosion = radioExplosion;
    }

    public float getDistanciaLanzamiento() {
        return distanciaLanzamiento;
    }

    public void setDistanciaLanzamiento(float distanciaLanzamiento) {
        this.distanciaLanzamiento = distanciaLanzamiento;
    }

    public float getAturdimiento() {
        return aturdimiento;
    }

    public void setAturdimiento(float aturdimiento) {
        this.aturdimiento = aturdimiento;
    }

    public float getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(float visibilidad) {
        this.visibilidad = visibilidad;
    }
}
