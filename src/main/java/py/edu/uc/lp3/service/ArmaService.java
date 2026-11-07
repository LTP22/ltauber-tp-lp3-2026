package py.edu.uc.lp3.service;

import java.util.List;

import py.edu.uc.lp3.domain.Arma;

public interface ArmaService {

    List<Arma> listar();

    Arma buscarPorId(Long id);

    Arma crear(Arma arma);

    Arma reasignar(Long id, String nuevoEquipo, float nuevoPrecio);

    void eliminar(Long id);

    String estadoDe(Long id);

    ResultadoCombate simularCombate(Long armaDeFuegoId, Long granadaId);
}
