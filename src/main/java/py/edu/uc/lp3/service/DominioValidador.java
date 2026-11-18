package py.edu.uc.lp3.service;

/**
 * Validador del dominio, movido fuera de las entidades (observación cerrada de la
 * corrección de POO-08: "el dominio, o sea la Entity, no debe lanzar excepciones ni
 * tener lógica de negocio"). Arma, ArmaDeFuego, Granada y Jugador ya no validan:
 * ArmaServiceImpl y JugadorService llaman a este validador antes de construir o
 * persistir. Las armas de catálogo (Pistola, Rifle, Subfusil, Francotirador,
 * GranaFlash, GranaHumo, GranaDetonadora) no son parte de la familia persistida
 * (no son @Entity), pero siguen necesitando rechazar valores ilegales para no romper
 * /arma/{tipo}: llaman a este mismo validador después de super(), así la regla queda
 * escrita una sola vez y vive en la capa service, no en el dominio.
 */
public final class DominioValidador {

    private DominioValidador() {
    }

    public static void validarComunes(int danio, float precio, String equipo, float peso) {
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        if (!"T".equals(equipo) && !"CT".equals(equipo)) {
            throw new IllegalArgumentException("El equipo debe ser T o CT");
        }
    }

    public static void validarArmaDeFuego(float precision, int balasCargador, int cargadores,
                                           float retroceso, float tiempoRecarga, String animacion) {
        if (precision < 0 || precision > 1) {
            throw new IllegalArgumentException("La precision debe estar entre 0 y 1");
        }
        if (balasCargador <= 0) {
            throw new IllegalArgumentException("El cargador debe tener al menos una bala");
        }
        if (cargadores < 0) {
            throw new IllegalArgumentException("La cantidad de cargadores no puede ser negativa");
        }
        if (retroceso < 0 || tiempoRecarga < 0) {
            throw new IllegalArgumentException("Retroceso y tiempo de recarga no pueden ser negativos");
        }
        if (animacion == null || animacion.isBlank()) {
            throw new IllegalArgumentException("La animacion es obligatoria");
        }
    }

    public static void validarGranada(float radioExplosion, float distanciaLanzamiento,
                                       float aturdimiento, float visibilidad) {
        if (radioExplosion < 0 || distanciaLanzamiento < 0) {
            throw new IllegalArgumentException("Radio y distancia no pueden ser negativos");
        }
        if (aturdimiento < 0 || visibilidad < 0) {
            throw new IllegalArgumentException("Aturdimiento y visibilidad no pueden ser negativos");
        }
    }

    public static void validarReasignacion(String nuevoEquipo, float nuevoPrecio) {
        if (!"T".equals(nuevoEquipo) && !"CT".equals(nuevoEquipo)) {
            throw new IllegalArgumentException("El equipo debe ser T o CT");
        }
        if (nuevoPrecio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
    }

    public static void validarNombreJugador(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del jugador es obligatorio");
        }
    }
}
