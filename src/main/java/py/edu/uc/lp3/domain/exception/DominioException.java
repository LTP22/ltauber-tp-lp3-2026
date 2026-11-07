package py.edu.uc.lp3.domain.exception;

/**
 * Base de la jerarquia de excepciones del dominio. Es unchecked (RuntimeException)
 * a proposito: Spring la intercepta igual con un @RestControllerAdvice sin forzar
 * un try/catch en cada metodo del service o del controller, y estos errores son
 * reglas de negocio rotas, no condiciones que el llamador pueda resolver ahi mismo.
 */
public abstract class DominioException extends RuntimeException {

    protected DominioException(String mensaje) {
        super(mensaje);
    }
}
