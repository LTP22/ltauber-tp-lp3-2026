package py.edu.uc.lp3.LT_taller_git_2026.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Corrección de casing: LT en mayúsculas
import py.edu.uc.lp3.LT_taller_git_2026.cs2.Arma;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.Pistola;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.Rifle;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.Francotirador;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.GranaFlash;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.GranaHumo;
import py.edu.uc.lp3.LT_taller_git_2026.cs2.GranaDetonadora;
@RestController
@RequestMapping("/arma")
public class ArmaController {

    @GetMapping("/pistola")
    public Arma crearPistola(
            @RequestParam(defaultValue = "25") int danio,
            @RequestParam(defaultValue = "500") float precio,
            @RequestParam(defaultValue = "T") String equipo,
            @RequestParam(defaultValue = "1.5") float peso,
            @RequestParam(defaultValue = "0.75") float precision,
            @RequestParam(defaultValue = "12") int balasCargador,
            @RequestParam(defaultValue = "3") int cargadores,
            @RequestParam(defaultValue = "0.3") float retroceso,
            @RequestParam(defaultValue = "0.5") float tiempoRecarga,
            @RequestParam(defaultValue = "pistol_fire") String animacion) {
        return new Pistola(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    @GetMapping("/rifle")
    public Arma crearRifle(
            @RequestParam(defaultValue = "77") int danio,
            @RequestParam(defaultValue = "2100") float precio,
            @RequestParam(defaultValue = "T") String equipo,
            @RequestParam(defaultValue = "3.6") float peso,
            @RequestParam(defaultValue = "0.85") float precision,
            @RequestParam(defaultValue = "30") int balasCargador,
            @RequestParam(defaultValue = "5") int cargadores,
            @RequestParam(defaultValue = "0.75") float retroceso,
            @RequestParam(defaultValue = "2.0") float tiempoRecarga,
            @RequestParam(defaultValue = "rifle_fire") String animacion) {
        return new Rifle(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    @GetMapping("/francotirador")
    public Arma crearFrancotirador(
            @RequestParam(defaultValue = "115") int danio,
            @RequestParam(defaultValue = "4750") float precio,
            @RequestParam(defaultValue = "T") String equipo,
            @RequestParam(defaultValue = "4.9") float peso,
            @RequestParam(defaultValue = "0.95") float precision,
            @RequestParam(defaultValue = "10") int balasCargador,
            @RequestParam(defaultValue = "4") int cargadores,
            @RequestParam(defaultValue = "1.2") float retroceso,
            @RequestParam(defaultValue = "3.3") float tiempoRecarga,
            @RequestParam(defaultValue = "sniper_fire") String animacion) {
        return new Francotirador(danio, precio, equipo, peso, precision, balasCargador, cargadores, retroceso, tiempoRecarga, animacion);
    }

    @GetMapping("/granada-flash")
    public Arma crearGranaFlash(
            @RequestParam(defaultValue = "0") int danio,
            @RequestParam(defaultValue = "200") float precio,
            @RequestParam(defaultValue = "T") String equipo,
            @RequestParam(defaultValue = "0.3") float peso,
            @RequestParam(defaultValue = "25") float radioExplosion,
            @RequestParam(defaultValue = "15") float distanciaLanzamiento,
            @RequestParam(defaultValue = "3.0") float ceguera,
            @RequestParam(defaultValue = "100") float visibilidad) {
        return new GranaFlash(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, ceguera, visibilidad);
    }

    @GetMapping("/granada-humo")
    public Arma crearGranaHumo(
            @RequestParam(defaultValue = "0") int danio,
            @RequestParam(defaultValue = "300") float precio,
            @RequestParam(defaultValue = "T") String equipo,
            @RequestParam(defaultValue = "0.4") float peso,
            @RequestParam(defaultValue = "20") float radioExplosion,
            @RequestParam(defaultValue = "18") float distanciaLanzamiento,
            @RequestParam(defaultValue = "4.5") float duracionHumo,
            @RequestParam(defaultValue = "0") float visibilidad) {
        return new GranaHumo(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, duracionHumo, visibilidad);
    }

    @GetMapping("/granada-detonadora")
    public Arma crearGranaDetonadora(
            @RequestParam(defaultValue = "60") int danio,
            @RequestParam(defaultValue = "400") float precio,
            @RequestParam(defaultValue = "T") String equipo,
            @RequestParam(defaultValue = "0.45") float peso,
            @RequestParam(defaultValue = "18") float radioExplosion,
            @RequestParam(defaultValue = "20") float distanciaLanzamiento,
            @RequestParam(defaultValue = "2.0") float aturdimiento,
            @RequestParam(defaultValue = "100") float visibilidad) {
        return new GranaDetonadora(danio, precio, equipo, peso, radioExplosion, distanciaLanzamiento, aturdimiento, visibilidad);
    }
}
