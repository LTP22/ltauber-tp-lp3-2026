package py.edu.uc.lp3.lt_taller_git_2026;

public class Subfusil extends ArmaDeFuego {
    private String nombre;
    private int nivel;

    public Subfusil() {
    }

    public Subfusil(int precio, int nivel, String nombre) {
        this.precio = precio;
        this.nivel = nivel;
        this.nombre = nombre;
    }

    public void disparoAutomatico() {
        System.out.println("Disparo automatico con subfusil.");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
