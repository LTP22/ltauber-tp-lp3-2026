package py.edu.uc.lp3.domain.exception;

/** El recurso existe, pero la operacion pedida rompe una regla de juego (disparar sin balas, lanzar una granada dos veces). */
public class OperacionInvalidaException extends DominioException {

    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}
