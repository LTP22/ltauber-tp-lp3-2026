package py.edu.uc.lp3.domain;

public class Francotirador extends ArmaDeFuego {

    public Francotirador(String equipo) {
        this(115, 4750f, equipo, 4.9f, 0.95f, 10, 4, 1.2f, 3.3f, "sniper_fire");
    }

    public Francotirador(int danio, float precio, String equipo, float peso,
                         float precision, int balasCargador, int cargadores,
                         float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    public void disparoSinRuido() {
        System.out.println("Disparo silencioso con francotirador.");
    }
}
