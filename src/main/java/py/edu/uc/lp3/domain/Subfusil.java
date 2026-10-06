package py.edu.uc.lp3.domain;

public class Subfusil extends ArmaDeFuego {

    public Subfusil(String equipo) {
        this(20, 1200f, equipo, 2.2f, 0.70f, 25, 4, 0.5f, 1.5f, "smg_fire");
    }

    public Subfusil(int danio, float precio, String equipo, float peso,
                    float precision, int balasCargador, int cargadores,
                    float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    public void disparoAutomatico() {
        System.out.println("Disparo automatico con subfusil.");
    }
}
