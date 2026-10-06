package py.edu.uc.lp3.domain;

public class Pistola extends ArmaDeFuego {

    public Pistola(String equipo) {
        this(25, 500f, equipo, 1.5f, 0.75f, 12, 3, 0.3f, 0.5f, "pistol_fire");
    }

    public Pistola(int danio, float precio, String equipo, float peso,
                   float precision, int balasCargador, int cargadores,
                   float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    public void disparoUnico() {
        System.out.println("Disparo unico con pistola.");
    }

    public void rafaga() {
        System.out.println("Rafaga con pistola.");
    }
}
