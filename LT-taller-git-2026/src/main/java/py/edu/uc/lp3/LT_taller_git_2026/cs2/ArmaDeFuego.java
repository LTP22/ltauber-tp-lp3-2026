package py.edu.uc.lp3.lt_taller_git_2026.cs2;

public class ArmaDeFuego extends Arma {
    protected float precision;
    protected int balasCargador;
    protected int cargadores;
    protected float retroceso;
    protected float tiempoRecarga;
    protected String animacion;

    public ArmaDeFuego() {
    }

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

    public void setPrecision(float precision) {
        this.precision = precision;
    }

    public int getBalasCargador() {
        return balasCargador;
    }

    public void setBalasCargador(int balasCargador) {
        this.balasCargador = balasCargador;
    }

    public int getCargadores() {
        return cargadores;
    }

    public void setCargadores(int cargadores) {
        this.cargadores = cargadores;
    }

    public float getRetroceso() {
        return retroceso;
    }

    public void setRetroceso(float retroceso) {
        this.retroceso = retroceso;
    }

    public float getTiempoRecarga() {
        return tiempoRecarga;
    }

    public void setTiempoRecarga(float tiempoRecarga) {
        this.tiempoRecarga = tiempoRecarga;
    }

    public String getAnimacion() {
        return animacion;
    }

    public void setAnimacion(String animacion) {
        this.animacion = animacion;
    }
}
