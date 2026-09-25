package py.edu.uc.lp3.LT_taller_git_2026.cs2;


import java.util.ArrayList;
import java.util.List;

public class Jugador {
    protected String nombre;
    protected List<Arma> inventario;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.inventario = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Arma> getInventario() {
        return inventario;
    }

    public void agregarArma(Arma arma) {
        if (arma != null) {
            this.inventario.add(arma);
        }
    }

    public int contarArmas() {
        return this.inventario.size();
    }

    // Polimorfismo: Itera sobre las armas de fuego y dispara
    public int dispararConTodas() {
        int contador = 0;
        for (Arma arma : inventario) {
            if (arma instanceof ArmaDeFuego) {
                ((ArmaDeFuego) arma).disparar();
                contador++;
            }
        }
        return contador;
    }

    // Polimorfismo: Itera sobre las armas de fuego y recarga
    public void recargarTodas() {
        for (Arma arma : inventario) {
            if (arma instanceof ArmaDeFuego) {
                ((ArmaDeFuego) arma).recargar();
            }
        }
    }

    // Polimorfismo: Itera sobre las granadas y las lanza
    public int lanzarGranadas() {
        int contador = 0;
        for (Arma arma : inventario) {
            if (arma instanceof Granada) {
                ((Granada) arma).lanzar();
                contador++;
            }
        }
        return contador;
    }
}