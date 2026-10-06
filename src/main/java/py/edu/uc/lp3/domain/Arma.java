package py.edu.uc.lp3.domain;

public abstract class Arma {
    protected int danio;
    protected float precio;
    protected String equipo;
    protected float peso;

    protected Arma(int danio, float precio, String equipo, float peso) {
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        if (!"T".equals(equipo) && !"CT".equals(equipo)) {
            throw new IllegalArgumentException("El equipo debe ser T o CT");
        }
        this.danio = danio;
        this.precio = precio;
        this.equipo = equipo;
        this.peso = peso;
    }

    public abstract String getComportamiento();

    public void comprar() {
        System.out.println("Comprando arma.");
    }

    public void inspeccionar() {
        System.out.println("Inspeccionando arma.");
    }

    public int getDanio() {
        return danio;
    }

    public float getPrecio() {
        return precio;
    }

    public String getEquipo() {
        return equipo;
    }

    public float getPeso() {
        return peso;
    }
}
