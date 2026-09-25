package py.edu.uc.lp3.LT_taller_git_2026.web;

import org.springframework.web.bind.annotation.*;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jugador")
public class JugadorController {

    private final Map<String, Jugador> jugadores = new HashMap<>();

    // 1. POST /jugador?nombre=X
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

    // 2. POST /jugador/{id}/agregar-arma
    @PostMapping("/{id}/agregar-arma")
    public Map<String, Object> agregarArma(
            @PathVariable String id,
            @RequestParam String tipo
    ) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Jugador no encontrado");
            return error;
        }

        Arma arma = crearInstanciaArma(tipo);
        if (arma != null) {
            jugador.agregarArma(arma);
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("nuevoInventario", jugador.contarArmas());
        return respuesta;
    }

    // 3. GET /jugador/{id}
    @GetMapping("/{id}")
    public Map<String, Object> obtenerJugador(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Jugador no encontrado");
            return error;
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("nombre", jugador.getNombre());
        respuesta.put("inventario", jugador.getInventario());
        return respuesta;
    }

    // 4. POST /jugador/{id}/disparar
    @PostMapping("/{id}/disparar")
    public Map<String, Object> disparar(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Jugador no encontrado");
            return error;
        }

        int armasUsadas = jugador.dispararConTodas();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("accion", "disparo");
        respuesta.put("armasUsadas", armasUsadas);
        respuesta.put("resultado", "Se dispararon todas las armas de fuego en el inventario");
        return respuesta;
    }

    // 5. POST /jugador/{id}/recargar
    @PostMapping("/{id}/recargar")
    public Map<String, Object> recargar(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Jugador no encontrado");
            return error;
        }

        jugador.recargarTodas();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("accion", "recarga");
        respuesta.put("municionRestaurada", true);
        return respuesta;
    }

    // 6. POST /jugador/{id}/lanzar-granadas
    @PostMapping("/{id}/lanzar-granadas")
    public Map<String, Object> lanzarGranadas(@PathVariable String id) {
        Jugador jugador = jugadores.get(id);
        if (jugador == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Jugador no encontrado");
            return error;
        }

        int granadasLanzadas = jugador.lanzarGranadas();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("jugador", jugador.getNombre());
        respuesta.put("accion", "lanzamiento");
        respuesta.put("granadaLanzadas", granadasLanzadas);
        return respuesta;
    }

    // Usa los constructores por defecto sin parámetros que tienen las clases base
    private Arma crearInstanciaArma(String tipo) {
        switch (tipo.toLowerCase()) {
            case "pistola":
                return new Pistola();
            case "rifle":
                return new Rifle();
            case "francotirador":
                return new Francotirador();
            case "granada-flash":
                return new GranaFlash();
            case "granada-humo":
                return new GranaHumo();
            case "granada-detonadora":
                return new GranaDetonadora();
            default:
                return null;
        }
    }
}