package py.edu.uc.lp3.lt_taller_git_2026.cs2;

public class Pistola extends ArmaDeFuego {

    public Pistola() {
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
