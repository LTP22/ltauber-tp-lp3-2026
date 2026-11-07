package py.edu.uc.lp3.rest.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uc.lp3.domain.Arma;
import py.edu.uc.lp3.domain.ArmaDeFuego;
import py.edu.uc.lp3.domain.Granada;
import py.edu.uc.lp3.rest.controller.dto.ArmaRequest;
import py.edu.uc.lp3.rest.controller.dto.ReasignacionRequest;
import py.edu.uc.lp3.service.ArmaService;

/**
 * CRUD REST sobre la familia persistida (Arma, ArmaDeFuego, Granada). El JSON arma a
 * mano el campo "tipo" segun el tipo real del objeto -mismo patron que ya usaba
 * JugadorController para "comportamiento"- en vez de anotar Arma con @JsonTypeInfo:
 * esa anotacion tambien afectaria a los endpoints viejos de /arma/{tipo} (que no son
 * parte de esta familia persistida y ya funcionan bien tal como estan).
 */
@RestController
@RequestMapping("/armas")
public class ArmaCrudController {

    @Autowired
    private ArmaService armaService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> crear(@RequestBody ArmaRequest req) {
        Arma arma = construir(req);
        Arma creada = armaService.crear(arma);
        return ResponseEntity.status(HttpStatus.CREATED).body(aJson(creada));
    }

    @GetMapping
    public List<Map<String, Object>> listar() {
        return armaService.listar().stream().map(this::aJson).toList();
    }

    @GetMapping("/{id}")
    public Map<String, Object> buscar(@PathVariable Long id) {
        return aJson(armaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public Map<String, Object> actualizar(@PathVariable Long id, @RequestBody ReasignacionRequest req) {
        return aJson(armaService.reasignar(id, req.getEquipo(), req.getPrecio()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        armaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/estado")
    public Map<String, Object> estado(@PathVariable Long id) {
        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("id", id);
        respuesta.put("estado", armaService.estadoDe(id));
        return respuesta;
    }

    private Arma construir(ArmaRequest req) {
        if (req.getTipo() == null) {
            throw new IllegalArgumentException("El campo 'tipo' es obligatorio (armaDeFuego o granada)");
        }
        return switch (req.getTipo()) {
            case "armaDeFuego" -> new ArmaDeFuego(
                    req.getDanio(), req.getPrecio(), req.getEquipo(), req.getPeso(),
                    valorOCero(req.getPrecision()), valorOCeroInt(req.getBalasCargador()),
                    valorOCeroInt(req.getCargadores()), valorOCero(req.getRetroceso()),
                    valorOCero(req.getTiempoRecarga()), req.getAnimacion());
            case "granada" -> new Granada(
                    req.getDanio(), req.getPrecio(), req.getEquipo(), req.getPeso(),
                    valorOCero(req.getRadioExplosion()), valorOCero(req.getDistanciaLanzamiento()),
                    valorOCero(req.getAturdimiento()), valorOCero(req.getVisibilidad()));
            default -> throw new IllegalArgumentException("Tipo de arma desconocido: " + req.getTipo());
        };
    }

    private float valorOCero(Float valor) {
        return valor == null ? 0f : valor;
    }

    private int valorOCeroInt(Integer valor) {
        return valor == null ? 0 : valor;
    }

    private Map<String, Object> aJson(Arma arma) {
        Map<String, Object> json = new LinkedHashMap<>();
        json.put("id", arma.getId());
        json.put("danio", arma.getDanio());
        json.put("precio", arma.getPrecio());
        json.put("equipo", arma.getEquipo());
        json.put("peso", arma.getPeso());
        json.put("comportamiento", arma.getComportamiento());

        if (arma instanceof ArmaDeFuego armaDeFuego) {
            json.put("tipo", "armaDeFuego");
            json.put("precision", armaDeFuego.getPrecision());
            json.put("balasCargador", armaDeFuego.getBalasCargador());
            json.put("balasEnCargador", armaDeFuego.getBalasEnCargador());
            json.put("cargadores", armaDeFuego.getCargadores());
            json.put("retroceso", armaDeFuego.getRetroceso());
            json.put("tiempoRecarga", armaDeFuego.getTiempoRecarga());
            json.put("animacion", armaDeFuego.getAnimacion());
        } else if (arma instanceof Granada granada) {
            json.put("tipo", "granada");
            json.put("radioExplosion", granada.getRadioExplosion());
            json.put("distanciaLanzamiento", granada.getDistanciaLanzamiento());
            json.put("aturdimiento", granada.getAturdimiento());
            json.put("visibilidad", granada.getVisibilidad());
            json.put("lanzada", granada.isLanzada());
        }
        return json;
    }
}
