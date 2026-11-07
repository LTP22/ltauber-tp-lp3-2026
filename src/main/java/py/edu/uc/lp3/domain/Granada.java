package py.edu.uc.lp3.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("granada")
public class Granada extends Arma implements Accionable {
    protected float radioExplosion;
    protected float distanciaLanzamiento;
    protected float aturdimiento;
    protected float visibilidad;
    private boolean lanzada;

    /** Constructor vacio para JPA, ver el de Arma. */
    protected Granada() {
        super();
    }

    /**
     * Antes era protected (solo la usaban las hijas concretas como GranaFlash). Ahora es
     * public porque el CRUD persistido necesita crear granadas genericas.
     */
    public Granada(int danio, float precio, String equipo, float peso,
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

    public boolean lanzar() {
        if (lanzada) {
            return false;
        }
        lanzada = true;
        return true;
    }

    public void explotar() {
        System.out.println("La granada exploto.");
    }

    public boolean isLanzada() {
        return lanzada;
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

    @Override
    public String describirEstado() {
        return lanzada ? "Ya fue lanzada." : "Lista para lanzar a " + distanciaLanzamiento + "m.";
    }
}
