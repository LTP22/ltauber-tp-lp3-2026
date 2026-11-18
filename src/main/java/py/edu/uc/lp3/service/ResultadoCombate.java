package py.edu.uc.lp3.service;

/** Resultado de una simulación de combate: cuánto daño hizo el disparo y en qué quedaron el arma y la granada usadas. */
public record ResultadoCombate(int danioInfligido, int balasRestantes, boolean granadaLanzada) {
}
