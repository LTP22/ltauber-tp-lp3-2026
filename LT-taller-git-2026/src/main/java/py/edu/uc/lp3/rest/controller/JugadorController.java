package py.edu.uc.lp3.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp3.domain.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    private final Map<String, Jugador> jugadores = new HashMap<>();

    @PostMapping
    public Map<String, Object> crearJugador(@RequestParam String nombre) {
        String id = String.valueOf(jugadores.size() + 1);
        Jugador jugador = new Jugador(nombre);
        jugadores.put(id, jugador);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("id", id);
        respuesta.put("nombre", jugador.getNombre());
        respuesta.put("armasEnInventario", jugador.contarArmas());
        return respuesta;
    }

    @PostMapping("/{id}/agregar-arma")
    public ResponseEntity<Map<String, Object>> agregarArma(
            @PathVariable String id,
            @RequestParam String tipo
    ) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            return noEncontrado();
        }

        Arma arma = crearInstanciaArma(tipo);
        if (arma == null) {
            return ResponseEntity.badRequest().body(Map.<String, Object>of("error", "Tipo de arma desconocido: " + tipo));
        }
        jugador.agregarArma(arma);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("nuevoInventario", jugador.contarArmas());
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerJugador(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            return noEncontrado();
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombre", jugador.getNombre());
        respuesta.put("inventario", jugador.getInventario());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/{id}/disparar")
    public ResponseEntity<Map<String, Object>> disparar(
            @PathVariable String id,
            @RequestParam(required = false) Float distancia
    ) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            return noEncontrado();
        }

        int armasUsadas = distancia == null
                ? jugador.dispararConTodas()
                : jugador.dispararConTodas(distancia);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("accion", "disparo");
        respuesta.put("distancia", distancia == null ? 0f : distancia);
        respuesta.put("armasUsadas", armasUsadas);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/{id}/recargar")
    public ResponseEntity<Map<String, Object>> recargar(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            return noEncontrado();
        }

        int armasRecargadas = jugador.recargarTodas();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("accion", "recarga");
        respuesta.put("armasRecargadas", armasRecargadas);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/{id}/lanzar-granadas")
    public ResponseEntity<Map<String, Object>> lanzarGranadas(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            return noEncontrado();
        }

        int granadasLanzadas = jugador.lanzarGranadas();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("accion", "lanzamiento");
        respuesta.put("granadasLanzadas", granadasLanzadas);
        return ResponseEntity.ok(respuesta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> valorInvalido(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }

    private ResponseEntity<Map<String, Object>> noEncontrado() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.<String, Object>of("error", "Jugador no encontrado"));
    }

    private Arma crearInstanciaArma(String tipo) {
        switch (tipo.toLowerCase()) {
            case "pistola":
                return new Pistola("T");
            case "rifle":
                return new Rifle("T");
            case "subfusil":
                return new Subfusil("T");
            case "francotirador":
                return new Francotirador("T");
            case "granada-flash":
                return new GranaFlash("T");
            case "granada-humo":
                return new GranaHumo("T");
            case "granada-detonadora":
                return new GranaDetonadora("T");
            default:
                return null;
        }
    }
}
