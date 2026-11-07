package py.edu.uc.lp3.domain;

/**
 * Protocolo de dominio (POO-07): cualquier arma que tenga una accion principal
 * puede describir su estado actual sin que quien pregunta sepa si es un arma
 * de fuego o una granada. Lo implementan ArmaDeFuego y Granada, las dos ramas
 * de la familia persistida, cada una con su propia nocion de "estado".
 */
public interface Accionable {
    String describirEstado();
}
