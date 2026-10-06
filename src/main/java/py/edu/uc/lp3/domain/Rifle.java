package py.edu.uc.lp3.domain;

public class Rifle extends ArmaDeFuego {

    public Rifle(String equipo) {
        this(77, 2100f, equipo, 3.6f, 0.85f, 30, 5, 0.75f, 2.0f, "rifle_fire");
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
