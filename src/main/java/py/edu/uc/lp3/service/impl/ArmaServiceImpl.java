package py.edu.uc.lp3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uc.lp3.domain.Accionable;
import py.edu.uc.lp3.domain.Arma;
import py.edu.uc.lp3.domain.ArmaDeFuego;
import py.edu.uc.lp3.domain.Granada;
import py.edu.uc.lp3.domain.exception.OperacionInvalidaException;
import py.edu.uc.lp3.domain.exception.RecursoNoEncontradoException;
import py.edu.uc.lp3.repository.ArmaRepository;
import py.edu.uc.lp3.service.ArmaService;
import py.edu.uc.lp3.service.DominioValidador;
import py.edu.uc.lp3.service.ResultadoCombate;

/**
 * Quién lanza la excepción: no el objeto del juego (disparar()/lanzar() siguen devolviendo
 * un resultado silencioso -0 balas, false- como en POO-06, para no romper las pruebas
 * unitarias que ya dependen de ese contrato) ni el controller (que solo traduce HTTP).
 * Es este service: conoce el repositorio (sabe si el id existe) y el dominio (sabe cuándo
 * una regla se rompe), así que es la capa que puede convertir una imposibilidad del juego
 * en una excepción del dominio.
 */
@Service
public class ArmaServiceImpl implements ArmaService {

    @Autowired
    private ArmaRepository armaRepository;

    @Override
    public List<Arma> listar() {
        return armaRepository.findAll();
    }

    @Override
    public Arma buscarPorId(Long id) {
        return armaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe un arma con id " + id));
    }

    @Override
    public Arma crear(Arma arma) {
        validar(arma);
        return armaRepository.save(arma);
    }

    @Override
    public Arma reasignar(Long id, String nuevoEquipo, float nuevoPrecio) {
        DominioValidador.validarReasignacion(nuevoEquipo, nuevoPrecio);
        Arma arma = buscarPorId(id);
        arma.reasignar(nuevoEquipo, nuevoPrecio);
        return armaRepository.save(arma);
    }

    /** Arma/ArmaDeFuego/Granada ya no validan (observación cerrada de POO-08): el service valida antes de persistir. */
    private void validar(Arma arma) {
        DominioValidador.validarComunes(arma.getDanio(), arma.getPrecio(), arma.getEquipo(), arma.getPeso());
        if (arma instanceof ArmaDeFuego armaDeFuego) {
            DominioValidador.validarArmaDeFuego(armaDeFuego.getPrecision(), armaDeFuego.getBalasCargador(),
                    armaDeFuego.getCargadores(), armaDeFuego.getRetroceso(), armaDeFuego.getTiempoRecarga(),
                    armaDeFuego.getAnimacion());
        } else if (arma instanceof Granada granada) {
            DominioValidador.validarGranada(granada.getRadioExplosion(), granada.getDistanciaLanzamiento(),
                    granada.getAturdimiento(), granada.getVisibilidad());
        }
    }

    @Override
    public void eliminar(Long id) {
        if (!armaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe un arma con id " + id);
        }
        armaRepository.deleteById(id);
    }

    @Override
    public String estadoDe(Long id) {
        Arma arma = buscarPorId(id);
        if (arma instanceof Accionable accionable) {
            return accionable.describirEstado();
        }
        throw new OperacionInvalidaException("El arma con id " + id + " no tiene un estado accionable");
    }

    @Override
    public ResultadoCombate simularCombate(Long armaDeFuegoId, Long granadaId) {
        Arma primera = buscarPorId(armaDeFuegoId);
        if (!(primera instanceof ArmaDeFuego armaDeFuego)) {
            throw new OperacionInvalidaException("El id " + armaDeFuegoId + " no corresponde a un arma de fuego");
        }
        Arma segunda = buscarPorId(granadaId);
        if (!(segunda instanceof Granada granada)) {
            throw new OperacionInvalidaException("El id " + granadaId + " no corresponde a una granada");
        }

        if (!armaDeFuego.tieneBalas()) {
            throw new OperacionInvalidaException("El arma de fuego con id " + armaDeFuegoId + " no tiene balas para disparar");
        }
        int danioInfligido = armaDeFuego.disparar();

        if (granada.isLanzada()) {
            throw new OperacionInvalidaException("La granada con id " + granadaId + " ya fue lanzada");
        }
        granada.lanzar();

        armaRepository.save(armaDeFuego);
        armaRepository.save(granada);

        return new ResultadoCombate(danioInfligido, armaDeFuego.getBalasEnCargador(), granada.isLanzada());
    }
}
