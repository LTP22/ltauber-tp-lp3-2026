package py.edu.uc.lp3.rest.controller.dto;

/**
 * DTO de transporte para crear armas via POST. No es una entidad de dominio, asi que sus
 * getters/setters publicos no comprometen el ocultamiento (ese se exige sobre Arma,
 * ArmaDeFuego y Granada, no sobre un objeto que solo sirve para traer datos del body JSON).
 * El campo "tipo" discrimina que constructor de negocio usar; lo interpreta el controller
 * a mano en vez de delegarlo a la deserializacion polimorfica de Jackson, para no tener
 * que relajar la visibilidad de los constructores de dominio mas de lo necesario.
 */
public class ArmaRequest {

    private String tipo;
    private int danio;
    private float precio;
    private String equipo;
    private float peso;

    // Solo para tipo = armaDeFuego
    private Float precision;
    private Integer balasCargador;
    private Integer cargadores;
    private Float retroceso;
    private Float tiempoRecarga;
    private String animacion;

    // Solo para tipo = granada
    private Float radioExplosion;
    private Float distanciaLanzamiento;
    private Float aturdimiento;
    private Float visibilidad;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Float getPrecision() {
        return precision;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    public Integer getBalasCargador() {
        return balasCargador;
    }

    public void setBalasCargador(Integer balasCargador) {
        this.balasCargador = balasCargador;
    }

    public Integer getCargadores() {
        return cargadores;
    }

    public void setCargadores(Integer cargadores) {
        this.cargadores = cargadores;
    }

    public Float getRetroceso() {
        return retroceso;
    }

    public void setRetroceso(Float retroceso) {
        this.retroceso = retroceso;
    }

    public Float getTiempoRecarga() {
        return tiempoRecarga;
    }

    public void setTiempoRecarga(Float tiempoRecarga) {
        this.tiempoRecarga = tiempoRecarga;
    }

    public String getAnimacion() {
        return animacion;
    }

    public void setAnimacion(String animacion) {
        this.animacion = animacion;
    }

    public Float getRadioExplosion() {
        return radioExplosion;
    }

    public void setRadioExplosion(Float radioExplosion) {
        this.radioExplosion = radioExplosion;
    }

    public Float getDistanciaLanzamiento() {
        return distanciaLanzamiento;
    }

    public void setDistanciaLanzamiento(Float distanciaLanzamiento) {
        this.distanciaLanzamiento = distanciaLanzamiento;
    }

    public Float getAturdimiento() {
        return aturdimiento;
    }

    public void setAturdimiento(Float aturdimiento) {
        this.aturdimiento = aturdimiento;
    }

    public Float getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Float visibilidad) {
        this.visibilidad = visibilidad;
    }
}
