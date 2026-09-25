package py.edu.uc.lp3.lt_taller_git_2026;

public class Francotirador extends ArmaDeFuego {

    public Francotirador() {
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
