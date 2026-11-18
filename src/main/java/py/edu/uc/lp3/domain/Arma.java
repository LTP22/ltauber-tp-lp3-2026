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
 * ArmaDeFuego y Granada, que ya sobreescribían getComportamiento() desde POO-06.
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

    private int danio;
    private float precio;
    private String equipo;
    private float peso;

    /**
     * Constructor vacío exigido por JPA para reconstruir el objeto al leerlo de la base.
     * Queda protected (no public) a propósito: ninguna clase fuera de este paquete puede
     * crear un arma sin invariantes: solo Hibernate, que instancia por reflexión y recién
     * después puebla los campos, antes de que cualquier otra capa vea el objeto.
     */
    protected Arma() {
    }

    /**
     * Sin validar: la Entity solo guarda estado (observación cerrada de POO-08).
     * Quien llama a este constructor -ArmaServiceImpl, o una hija de catálogo como
     * Pistola- es responsable de validar antes, con DominioValidador.
     */
    protected Arma(int danio, float precio, String equipo, float peso) {
        this.danio = danio;
        this.precio = precio;
        this.equipo = equipo;
        this.peso = peso;
    }

    public abstract String getComportamiento();

    /**
     * Único mensaje que puede cambiar precio y equipo después de construida el arma.
     * Es lo que usa el PUT del CRUD: no reemplaza el arma entera (eso rompería el daño,
     * la precisión, etc., que son intrínsecos al arma y se protegen reconstruyendo el
     * objeto si hace falta cambiarlos), solo reasigna los dos campos que tiene sentido
     * tocar sin violar las reglas de construcción. Sin validar: ArmaServiceImpl valida
     * antes de llamarlo.
     */
    public void reasignar(String nuevoEquipo, float nuevoPrecio) {
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
