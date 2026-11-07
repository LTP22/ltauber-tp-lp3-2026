# Especificaciones: CRUD REST, H2, reglas de negocio y excepciones (POO-08)

**Alumno:** Luis Tauber (LTP22)
**Asignatura:** Lenguaje de Programación 3 (CYT646), ejercicio POO-08
**Dominio:** Counter-Strike 2
**Repositorio:** https://github.com/LTP22/ltauber-tp-lp3-2026
**Commit de la solución:** https://github.com/LTP22/ltauber-tp-lp3-2026/commit/e602970

---

## 1. Objetivo

Extender la API REST de armas de CS2 (POO-06) con persistencia en H2. El servicio ahora demuestra, además de lo de POO-06:

- Persistencia de una familia de tres clases (`Arma`, `ArmaDeFuego`, `Granada`) con JPA e inheritance `SINGLE_TABLE`.
- CRUD REST completo sobre esa familia.
- Dos reglas de negocio del dominio que alimentan una simulación persistida ("combate": disparar + lanzar granada).
- Una jerarquía de excepciones del dominio (`DominioException` → `RecursoNoEncontradoException` / `OperacionInvalidaException`), con un handler HTTP por tipo.
- Un protocolo de dominio (`interface Accionable`), implementado por las dos ramas de la familia.
- Una colección de Insomnia versionada en el repositorio.

## 2. Dominio

```
Arma (abstracta, @Entity, SINGLE_TABLE)         getComportamiento() abstracto, reasignar(equipo, precio)
├── ArmaDeFuego (@Entity)                       sobreescribe getComportamiento(); implements Accionable
│   ├── Pistola, Rifle, Subfusil, Francotirador  (no persistidas; catálogo /arma/{tipo} sin cambios)
└── Granada (@Entity)                           sobreescribe getComportamiento(); implements Accionable
    ├── GranaFlash, GranaHumo, GranaDetonadora   (no persistidas; catálogo /arma/{tipo} sin cambios)

Accionable (interface)          describirEstado() String
ArmaRepository (interface)      extends JpaRepository<Arma, Long>
ArmaService (interface)         CRUD + reglas de negocio + simulación, implementada por ArmaServiceImpl
DominioException (abstracta)
├── RecursoNoEncontradoException    (404)
└── OperacionInvalidaException      (409)
```

Se persiste la familia que ya existía desde POO-06 (`Arma` + sus dos hijas directas `ArmaDeFuego`/`Granada`, que ya sobreescribían `getComportamiento()`), en vez de inventar una familia nueva: así la jerarquía persistida sigue demostrando el mismo método abstracto, sin duplicar conceptos ya probados. Las hijas concretas (`Pistola`, `GranaFlash`, etc.) siguen existiendo tal como estaban, para el catálogo `/arma/{tipo}` y el inventario de `Jugador`; no son parte de la familia persistida.

## 3. Consignas y cómo se cumplen

| # | Consigna | Dónde se cumple |
|---|---|---|
| 1 | Dependencias JPA/H2, consola habilitada, propiedades explicadas | `pom.xml` (`spring-boot-starter-data-jpa`, `h2`); `application.properties`; README sección "Persistencia en H2" |
| 2 | Tres clases persistidas (familia padre + 2 hijas), constructor vacío para JPA | `Arma`/`ArmaDeFuego`/`Granada` con `@Entity`; constructor `protected` sin argumentos en cada una |
| 3 | Interfaz de dominio (POO-07), ≥2 clases de ramas distintas la implementan, endpoint de lectura la usa | `Accionable.describirEstado()`; `ArmaDeFuego` y `Granada` la implementan; `GET /armas/{id}/estado` |
| 4 | Repositorio Spring Data para la clase base | `ArmaRepository extends JpaRepository<Arma, Long>` |
| 5 | CRUD REST (`POST`/`GET` colección/`GET` por id/`PUT`/`DELETE`), JSON discrimina el subtipo | `ArmaCrudController` sobre `/armas` |
| 6 | `GET /` confirma que el servicio vive | `IndexController` (sin cambios) |
| 7 | Dos reglas de negocio + simulación persistida, involucra las tres clases | `ArmaDeFuego.disparar()` + `Granada.lanzar()`, orquestadas en `ArmaServiceImpl.simularCombate()`; `POST /simulaciones/combate` |
| 8 | Jerarquía de excepciones (base + 2 hijas) + handler por tipo | `DominioException` → `RecursoNoEncontradoException` (404) / `OperacionInvalidaException` (409); `DominioExceptionHandler` |
| 9 | Colección de Insomnia con CRUD, POST ilegal, id inexistente, endpoint de la interfaz, simulación, una petición por excepción hija | `insomnia/CS2-POO08-Insomnia.json` (13 pedidos numerados) |
| 10 | Diagrama Mermaid al día (clases, interfaz, repositorio, controller, excepciones) | `README.md`, diagrama "Persistencia, protocolo y excepciones (POO-08)" |
| 11 | Bitácora de IA | `BITACORA.md` |
| 12 | Commits separados por funcionalidad | Historial de `git log` de este repositorio |

## 4. Invariantes

- Los constructores de `Arma`/`ArmaDeFuego`/`Granada` siguen validando lo mismo que en POO-06 (daño, precio, equipo, peso, precisión, cargador, etc.) y lanzan `IllegalArgumentException` → `400`.
- No hay setters públicos. El único mensaje que cambia estado desde fuera de la lógica de combate es `Arma.reasignar(equipo, precio)` (usado por `PUT`), que revalida las mismas reglas que el constructor.
- El constructor vacío que exige JPA es `protected`: ninguna clase fuera del paquete `domain` puede crear un arma sin invariantes; solo Hibernate, por reflexión.
- `ArmaDeFuego.disparar()` y `Granada.lanzar()` conservan el contrato de POO-06 (devuelven `0`/`false` en vez de lanzar una excepción), para no romper los tests unitarios existentes. La capa que convierte esa imposibilidad en una excepción HTTP (`OperacionInvalidaException`) es `ArmaServiceImpl`, no el objeto del dominio ni el controller.
- Un id inexistente nunca es `IllegalArgumentException`: es `RecursoNoEncontradoException` (404), un tipo de excepción distinto, porque el problema es otro (no hay nada que validar, directamente no hay recurso).

## 5. Cómo probar

### Requisitos
- Java 21
- Git
- (opcional) Insomnia, para importar la colección

### Pasos

```bash
git clone https://github.com/LTP22/ltauber-tp-lp3-2026.git
cd ltauber-tp-lp3-2026
./mvnw test
./mvnw spring-boot:run
```

El servicio queda disponible en `http://localhost:8080`. La base H2 es en memoria (`create-drop`): se recrea vacía en cada arranque. La consola de H2 está en `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:cs2db`, usuario `sa`, sin contraseña).

### CRUD (PowerShell: usar `curl.exe`, no el alias `curl`)

```bash
# Crear un arma de fuego -> 201, devuelve el id
curl.exe -X POST http://localhost:8080/armas -H "Content-Type: application/json" -d "{\"tipo\":\"armaDeFuego\",\"danio\":25,\"precio\":500,\"equipo\":\"T\",\"peso\":1.5,\"precision\":0.75,\"balasCargador\":12,\"cargadores\":3,\"retroceso\":0.3,\"tiempoRecarga\":0.5,\"animacion\":\"pistol_fire\"}"

# Crear una granada -> 201, devuelve el id
curl.exe -X POST http://localhost:8080/armas -H "Content-Type: application/json" -d "{\"tipo\":\"granada\",\"danio\":0,\"precio\":200,\"equipo\":\"T\",\"peso\":0.3,\"radioExplosion\":25,\"distanciaLanzamiento\":15,\"aturdimiento\":3,\"visibilidad\":100}"

# Listar / leer uno (reemplazar {id})
curl.exe http://localhost:8080/armas
curl.exe http://localhost:8080/armas/{id}

# Actualizar (solo equipo y precio)
curl.exe -X PUT http://localhost:8080/armas/{id} -H "Content-Type: application/json" -d "{\"equipo\":\"CT\",\"precio\":450}"

# Borrar, y confirmar 404 despues
curl.exe -X DELETE http://localhost:8080/armas/{id}
curl.exe -i http://localhost:8080/armas/{id}

# POST ilegal -> 400 (no se persiste)
curl.exe -i -X POST http://localhost:8080/armas -H "Content-Type: application/json" -d "{\"tipo\":\"armaDeFuego\",\"danio\":-5,\"precio\":500,\"equipo\":\"T\",\"peso\":1.5,\"precision\":0.75,\"balasCargador\":12,\"cargadores\":3,\"retroceso\":0.3,\"tiempoRecarga\":0.5,\"animacion\":\"x\"}"
```

### Interfaz de dominio y simulación

```bash
# Endpoint que usa Accionable (sirve para armaDeFuego o granada, mismo endpoint)
curl.exe http://localhost:8080/armas/{id}/estado

# Simulacion de combate (usa un id de armaDeFuego y uno de granada)
curl.exe -X POST "http://localhost:8080/simulaciones/combate?armaDeFuegoId={id}&granadaId={id}"
# -> {"danioInfligido":19,"balasRestantes":11,"granadaLanzada":true}

# Repetir con la misma granada -> 409 (OperacionInvalidaException)
curl.exe -i -X POST "http://localhost:8080/simulaciones/combate?armaDeFuegoId={id}&granadaId={id}"

# Id inexistente -> 404 (RecursoNoEncontradoException)
curl.exe -i http://localhost:8080/armas/9999
```

### Insomnia

Importar `insomnia/CS2-POO08-Insomnia.json` (`Create` → `Import From File`). Trae un `Base Environment` con las variables de id y 13 pedidos numerados que hay que correr en orden (crear primero, copiar los ids a las variables, simular, recién después provocar los errores). El detalle completo del orden está en el README, sección "Colección de Insomnia".

---

**Alumno:** Luis Tauber (LTP22)
**Fecha:** 2026-11-07
**Facultad:** Facultad de Ciencias y Tecnología
**Licencia:** Apache 2.0

## 6. Commit de la solución

El enlace exacto al commit que contiene la solución (código e Insomnia; ver también "Commit de las especificaciones" en el README, que apunta al commit que agrega este documento):

**https://github.com/LTP22/ltauber-tp-lp3-2026/commit/e602970**

---

**Alumno:** Luis Tauber (LTP22)
