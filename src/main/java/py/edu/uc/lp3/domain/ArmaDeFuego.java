package py.edu.uc.lp3.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("armaDeFuego")
public class ArmaDeFuego extends Arma implements Accionable {
    private static final float DISTANCIA_SIN_DANIO = 100f;

    private float precision;
    private int balasCargador;
    private int cargadores;
    private float retroceso;
    private float tiempoRecarga;
    private String animacion;
    private int balasEnCargador;

    /** Constructor vacío para JPA, ver el de Arma. */
    protected ArmaDeFuego() {
        super();
    }

    /**
     * Antes era protected (solo la usaban las hijas concretas como Pistola). Ahora es
     * public porque el CRUD persistido necesita crear armas de fuego genéricas, no
     * atadas a un modelo concreto como Pistola o Rifle. Sin validar (observación cerrada
     * de POO-08): ArmaServiceImpl valida con DominioValidador antes de construir.
     */
    public ArmaDeFuego(int danio, float precio, String equipo, float peso,
                          float precision, int balasCargador, int cargadores,
                          float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso);
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
        return Math.round(getDanio() * precision * factorDistancia);
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
