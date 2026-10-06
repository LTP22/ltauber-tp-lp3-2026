package py.edu.uc.lp3.domain;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    protected String nombre;
    protected List<Arma> inventario;

    public Jugador(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del jugador es obligatorio");
        }
        this.nombre = nombre;
        this.inventario = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Arma> getInventario() {
        return List.copyOf(inventario);
    }

    public void agregarArma(Arma arma) {
        if (arma == null) {
            throw new IllegalArgumentException("El arma no puede ser nula");
        }
        this.inventario.add(arma);
    }

    public int contarArmas() {
        return this.inventario.size();
    }

    public int dispararConTodas() {
        int contador = 0;
        for (Arma arma : inventario) {
            if (arma instanceof ArmaDeFuego armaDeFuego && armaDeFuego.tieneBalas()) {
                armaDeFuego.disparar();
                contador++;
            }
        }
        return contador;
    }

    public int dispararConTodas(float distancia) {
        int contador = 0;
        for (Arma arma : inventario) {
            if (arma instanceof ArmaDeFuego armaDeFuego && armaDeFuego.tieneBalas()) {
                armaDeFuego.disparar(distancia);
                contador++;
            }
        }
        return contador;
    }

    public int recargarTodas() {
        int contador = 0;
        for (Arma arma : inventario) {
            if (arma instanceof ArmaDeFuego armaDeFuego && armaDeFuego.recargar()) {
                contador++;
            }
        }
        return contador;
    }

    public int lanzarGranadas() {
        int contador = 0;
        for (Arma arma : inventario) {
            if (arma instanceof Granada granada && granada.lanzar()) {
                contador++;
            }
        }
        return contador;
    }
}
