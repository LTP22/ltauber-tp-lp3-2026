package py.edu.uc.lp3.rest.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import py.edu.uc.lp3.domain.exception.OperacionInvalidaException;
import py.edu.uc.lp3.domain.exception.RecursoNoEncontradoException;

/**
 * Un handler por tipo de excepcion, para que el cliente distinga a las hermanas por el
 * codigo HTTP: 404 cuando el recurso no existe, 409 cuando existe pero la operacion pedida
 * rompe una regla de juego. Tambien centraliza aqui el manejo de IllegalArgumentException
 * (400, valores que rompen un invariante de construccion): ArmaController y JugadorController
 * ya tenian su propio @ExceptionHandler local para eso, pero los controllers nuevos
 * (ArmaCrudController, SimulacionController) no lo tenian, y sin este advice global un
 * POST invalido se iba como 500 en vez de 400. Un handler local en un controller sigue
 * teniendo prioridad sobre este si existe, asi que no cambia el comportamiento de los
 * controllers viejos.
 */
@RestControllerAdvice
public class DominioExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> recursoNoEncontrado(RecursoNoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(OperacionInvalidaException.class)
    public ResponseEntity<Map<String, String>> operacionInvalida(OperacionInvalidaException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> valorInvalido(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }
}
