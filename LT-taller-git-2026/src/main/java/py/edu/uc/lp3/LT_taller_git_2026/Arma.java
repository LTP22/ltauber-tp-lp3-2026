package py.edu.uc.lp3.lt_taller_git_2026;

public abstract class Arma {
    protected int danio;
    protected float precio;
    protected String equipo;
    protected float peso;

    public Arma() {
    }

    public Arma(int danio, float precio, String equipo, float peso) {
        this.danio = danio;
        this.precio = precio;
        this.equipo = equipo;
        this.peso = peso;
    }

    public abstract String getComportamiento();

    public void comprar() {
        System.out.println("Comprando arma.");
    }

    public void inspeccionar() {
        System.out.println("Inspeccionando arma.");
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
}
