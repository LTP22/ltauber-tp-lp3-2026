package py.edu.uc.lp3.domain.exception;

/**
 * Base de la jerarquía de excepciones del dominio. Es unchecked (RuntimeException)
 * a propósito: Spring la intercepta igual con un @RestControllerAdvice sin forzar
 * un try/catch en cada método del service o del controller, y estos errores son
 * reglas de negocio rotas, no condiciones que el llamador pueda resolver ahí mismo.
 */
public abstract class DominioException extends RuntimeException {

    protected DominioException(String mensaje) {
        super(mensaje);
    }
}
