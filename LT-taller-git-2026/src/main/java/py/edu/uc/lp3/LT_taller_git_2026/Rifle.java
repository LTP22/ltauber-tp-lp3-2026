package py.edu.uc.lp3.lt_taller_git_2026;

public class Rifle extends ArmaDeFuego {

    public Rifle() {
    }

    public Rifle(int danio, float precio, String equipo, float peso,
                 float precision, int balasCargador, int cargadores,
                 float retroceso, float tiempoRecarga, String animacion) {
        super(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    public void rafagaAutomatica() {
        System.out.println("Rafaga automatica con rifle.");
    }
}
