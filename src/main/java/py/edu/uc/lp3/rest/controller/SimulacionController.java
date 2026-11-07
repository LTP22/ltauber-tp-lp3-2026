package py.edu.uc.lp3.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uc.lp3.service.ArmaService;
import py.edu.uc.lp3.service.ResultadoCombate;

/**
 * Simulacion de combate: aplica las dos reglas de negocio (disparar consume una bala y
 * calcula dano; lanzar una granada la marca como usada, sin vuelta atras) sobre un arma
 * de fuego y una granada ya persistidas, y guarda el resultado. Un GET posterior a
 * /armas/{id} muestra el estado actualizado de cada una.
 */
@RestController
@RequestMapping("/simulaciones")
public class SimulacionController {

    @Autowired
    private ArmaService armaService;

    @PostMapping("/combate")
    public ResultadoCombate combate(@RequestParam Long armaDeFuegoId, @RequestParam Long granadaId) {
        return armaService.simularCombate(armaDeFuegoId, granadaId);
    }
}
