package py.edu.uc.lp3.domain;

public class ArmaDeFuego extends Arma {
    protected float precision;
    protected int balasCargador;
    protected int cargadores;
    protected float retroceso;
    protected float tiempoRecarga;
    protected String animacion;

    protected ArmaDeFuego(int danio, float precio, String equipo, float peso,
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
    }

    @Override
    public String getComportamiento() {
        return "Dispara con precision " + precision + " y retroceso " + retroceso + ".";
    }

    public void disparar() {
        System.out.println("Disparando arma de fuego.");
    }

    public void recargar() {
        System.out.println("Recargando arma de fuego.");
    }

    public float getPrecision() {
        return precision;
    }

    public int getBalasCargador() {
        return balasCargador;
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
}
