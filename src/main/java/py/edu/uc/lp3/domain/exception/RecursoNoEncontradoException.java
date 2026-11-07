package py.edu.uc.lp3.domain.exception;

/** El id pedido no existe en la base. Distinta de una regla de juego incumplida: no hay nada que validar, directamente no hay recurso. */
public class RecursoNoEncontradoException extends DominioException {

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
