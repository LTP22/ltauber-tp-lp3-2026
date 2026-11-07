package py.edu.uc.lp3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.uc.lp3.domain.Arma;

/**
 * Interfaz: nosotros declaramos los mensajes, Spring Data genera la implementacion
 * en tiempo de arranque. El controller (via ArmaService) depende de este tipo
 * abstracto, no de una implementacion concreta de persistencia: mismo principio
 * que el protocolo de dominio de POO-07, aplicado a la capa de datos.
 */
public interface ArmaRepository extends JpaRepository<Arma, Long> {

    List<Arma> findByEquipo(String equipo);
}
