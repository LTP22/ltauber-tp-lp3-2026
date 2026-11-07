package py.edu.uc.lp3.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("armaDeFuego")
public class ArmaDeFuego extends Arma implements Accionable {
    private static final float DISTANCIA_SIN_DANIO = 100f;

    protected float precision;
    protected int balasCargador;
    protected int cargadores;
    protected float retroceso;
    protected float tiempoRecarga;
    protected String animacion;
    private int balasEnCargador;

    /** Constructor vacio para JPA, ver el de Arma. */
    protected ArmaDeFuego() {
        super();
    }

    /**
     * Antes era protected (solo la usaban las hijas concretas como Pistola). Ahora es
     * public porque el CRUD persistido necesita crear armas de fuego genericas, no
     * atadas a un modelo concreto como Pistola o Rifle.
     */
    public ArmaDeFuego(int danio, float precio, String equipo, float peso,
                          float precision, int balasCargador, int cargadores,
                          float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso);
        if (precision < 0 || precision > 1) {
            throw new IllegalArgumentException("La precision debe estar entre 0 y 1");
        }
        if (balasCargador <= 0) {
            throw new IllegalArgumentException("El cargador debe tener al menos una bala");
        }
        if (cargadores < 0) {
            throw new IllegalArgumentException("La cantidad de cargadores no puede ser negativa");
        }
        if (retroceso < 0 || tiempoRecarga < 0) {
            throw new IllegalArgumentException("Retroceso y tiempo de recarga no pueden ser negativos");
        }
        if (animacion == null || animacion.isBlank()) {
            throw new IllegalArgumentException("La animacion es obligatoria");
        }
        this.precision = precision;
        this.balasCargador = balasCargador;
        this.cargadores = cargadores;
        this.retroceso = retroceso;
        this.tiempoRecarga = tiempoRecarga;
        this.animacion = animacion;
        this.balasEnCargador = balasCargador;
    }

    @Override
    public String getComportamiento() {
        return "Dispara con precision " + precision + " y retroceso " + retroceso + ".";
    }

    public int disparar() {
        return disparar(0f);
    }

    public int disparar(float distancia) {
        if (distancia < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }
        if (balasEnCargador == 0) {
            return 0;
        }
        balasEnCargador--;
        float factorDistancia = Math.max(0f, 1f - distancia / DISTANCIA_SIN_DANIO);
        return Math.round(danio * precision * factorDistancia);
    }

    public boolean recargar() {
        if (balasEnCargador == balasCargador || cargadores == 0) {
            return false;
        }
        cargadores--;
        balasEnCargador = balasCargador;
        return true;
    }

    public boolean tieneBalas() {
        return balasEnCargador > 0;
    }

    public float getPrecision() {
        return precision;
    }

    public int getBalasCargador() {
        return balasCargador;
    }

    public int getBalasEnCargador() {
        return balasEnCargador;
    }

    public int getCargadores() {
        return cargadores;
    }

    public float getRetroceso() {
        return retroceso;
    }

    public float getTiempoRecarga() {
        return tiempoRecarga;
    }

    public String getAnimacion() {
        return animacion;
    }

    @Override
    public String describirEstado() {
        if (tieneBalas()) {
            return "Tiene " + balasEnCargador + "/" + balasCargador + " balas en el cargador y "
                    + cargadores + " cargadores de repuesto.";
        }
        return "Sin balas en el cargador; quedan " + cargadores + " cargadores para recargar.";
    }
}
