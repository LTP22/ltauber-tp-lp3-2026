package py.edu.uc.lp3.service.impl;

import org.springframework.stereotype.Service;

import py.edu.uc.lp3.domain.Jugador;
import py.edu.uc.lp3.service.DominioValidador;
import py.edu.uc.lp3.service.JugadorService;

/** Jugador no es un @Entity (su inventario sigue en memoria, en JugadorController), pero es dominio igual: valida aquí, no en el constructor (observación cerrada de POO-08). */
@Service
public class JugadorServiceImpl implements JugadorService {

    @Override
    public Jugador crear(String nombre) {
        DominioValidador.validarNombreJugador(nombre);
        return new Jugador(nombre);
    }
}
