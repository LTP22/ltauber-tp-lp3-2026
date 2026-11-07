package py.edu.uc.lp3.domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Familia persistida en H2: Arma (padre) y sus dos hijas directas,
 * ArmaDeFuego y Granada, que ya sobreescribian getComportamiento() desde POO-06.
 * Single table: la familia es chica (tres clases) y evita JOINs para un CRUD simple;
 * el costo es que la tabla tiene columnas nulas para los atributos exclusivos de cada hija.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected int danio;
    protected float precio;
    protected String equipo;
    protected float peso;

    /**
     * Constructor vacio exigido por JPA para reconstruir el objeto al leerlo de la base.
     * Queda protected (no public) a proposito: ninguna clase fuera de este paquete puede
     * crear un arma sin invariantes: solo Hibernate, que instancia por reflexion y recien
     * despues puebla los campos, antes de que cualquier otra capa vea el objeto.
     */
    protected Arma() {
    }

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

    /**
     * Unico mensaje que puede cambiar precio y equipo despues de construida el arma.
     * Es lo que usa el PUT del CRUD: no reemplaza el arma entera (eso rompería el daño,
     * la precision, etc., que son intrinsecos al arma y se protegen reconstruyendo el
     * objeto si hace falta cambiarlos), solo reasigna los dos campos que tiene sentido
     * tocar sin violar las reglas de construccion.
     */
    public void reasignar(String nuevoEquipo, float nuevoPrecio) {
        if (!"T".equals(nuevoEquipo) && !"CT".equals(nuevoEquipo)) {
            throw new IllegalArgumentException("El equipo debe ser T o CT");
        }
        if (nuevoPrecio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.equipo = nuevoEquipo;
        this.precio = nuevoPrecio;
    }

    public Long getId() {
        return id;
    }

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
