package py.edu.uc.lp3.service;

/** Resultado de una simulacion de combate: cuanto dano hizo el disparo y en que quedaron el arma y la granada usadas. */
public record ResultadoCombate(int danioInfligido, int balasRestantes, boolean granadaLanzada) {
}
