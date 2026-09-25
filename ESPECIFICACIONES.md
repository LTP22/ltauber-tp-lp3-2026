# Especificaciones del Taller: API REST de Armas Counter-Strike 2

**Asignatura:** Lenguaje de Programación 3 (CYT646)  
**Edición:** 2026  
**Opción:** B — Counter-Strike 2

---

## Objetivo General

Publicar un servicio HTTP propio (Spring Boot, API REST) que versiona el modelado de las clases de armas de Counter-Strike 2 (del 2 y 3 de septiembre), demostrando herencia, polimorfismo mediante métodos abstractos, ocultamiento de información, y la separación entre capas (dominio vs. controllers HTTP).

---

## Consignas Requeridas

### (1) Repositorio en GitHub
- ✅ Crear repositorio público con nombre `INICIALES-taller-git-2026` (ej: `LT-taller-git-2026`)
- ✅ README inicial (creado por GitHub)
- ✅ Licencia Apache 2.0
- ✅ Clonar por HTTPS

**Estado:** Completado. Repositorio en: `https://github.com/LTP22/ltauber-tp-lp3-2026`

### (2) Proyecto Spring Boot
- ✅ Maven, Java 21, Spring Web
- ✅ Generado en start.spring.io
- ✅ Verificación: `./mvnw spring-boot:run` levanta correctamente en `http://localhost:8080`

**Dependencies usadas:**
- `spring-boot-starter-web` — para API REST
- `spring-boot-starter-test` — para testing (scope: test)

### (3) Modelado previo (Clases del dominio)
- ✅ Clases copiadas de POO-02/POO-03 y compilando
- ✅ Estructura:
  - `cs2/` — paquete del dominio
    - `Arma` (abstracta, clase base)
    - `ArmaDeFuego` (extiende Arma)
      - `Pistola`, `Rifle`, `Subfusil`, `Francotirador`
    - `Granada` (abstracta, extiende Arma)
      - `GranaFlash`, `GranaHumo`, `GranaDetonadora`
  - `web/` — paquete de controllers HTTP

**Total de clases:** 10 (1 raíz abstracta + 1 intermedia abstracta + 8 concretas)

### (4) Controllers HTTP
#### IndexController (GET /)
- Retorna: `"API REST de Armas de Counter-Strike 2"`
- Propósito: Confirmar que el servicio está vivo
- Ubicación: `web/IndexController.java`

#### ArmaController (Construcción por URL)
- **6 endpoints** que construyen armas desde parámetros de URL (`@RequestParam`)
- Cada endpoint devuelve una instancia de `Arma` (tipo padre, no específico)
- **Sin `if` por tipo:** el controller simplemente llama al constructor correspondiente
- Endpoints:
  - `GET /arma/pistola?danio=25&precio=500&...` → Pistola con valores por defecto modificables
  - `GET /arma/rifle?danio=77&precio=2100&...` → Rifle
  - `GET /arma/francotirador?danio=115&precio=4750&...` → Francotirador (Sniper)
  - `GET /arma/granada-flash?danio=0&precio=200&...` → GranaFlash
  - `GET /arma/granada-humo?danio=0&precio=300&...` → GranaHumo
  - `GET /arma/granada-detonadora?danio=60&precio=400&...` → GranaDetonadora

### (5) Método Abstracto y Polimorfismo

#### Clase Base: `Arma` (abstracta)
```java
public abstract class Arma {
    protected int danio;
    protected float precio;
    protected String equipo;
    protected float peso;
    
    public abstract String getComportamiento();
    // ... getters/setters
}
```

#### Dos Hijas Independientes Implementan `getComportamiento()`:

**ArmaDeFuego** (extiende Arma):
```java
@Override
public String getComportamiento() {
    return "Dispara con precision " + precision + " y retroceso " + retroceso + ".";
}
```

**Granada** (extiende Arma, también abstracta):
```java
@Override
public String getComportamiento() {
    return "Se lanza a " + distanciaLanzamiento + "m y explota con radio " + radioExplosion + "m.";
}
```

#### Nietas especializadas:
- **GranaFlash**: `"Destella con intensidad ... a ...m, cegando a enemigos en radio ...m."`
- **GranaHumo**: `"Crea cortina de humo durante ...s en radio ...m, ocultando visibilidad a ...m."`
- **GranaDetonadora**: `"Explota con dano ... en radio ...m, aturdiendo ...s y causando visibilidad ..."`
- **Pistola, Rifle, Subfusil, Francotirador**: Heredan comportamiento de `ArmaDeFuego`

#### Demostración en JSON:
El controller declara variables de tipo `Arma` (padre) pero construye instancias específicas:
```java
public Arma crearPistola(...) {
    return new Pistola(...);  // Tipo declarado: Arma, Tipo real: Pistola
}
```

Cuando Spring serializa a JSON, el campo `comportamiento` refleja el resultado de `getComportamiento()` del objeto real (polimorfismo en acción):
```json
{
  "danio": 25,
  "precio": 500.0,
  "equipo": "T",
  "peso": 1.5,
  "precision": 0.75,
  "balasCargador": 12,
  "cargadores": 3,
  "retroceso": 0.3,
  "tiempoRecarga": 0.5,
  "animacion": "pistol_fire",
  "comportamiento": "Dispara con precision 0.75 y retroceso 0.3."
}
```

**Sin `if` en el controller:** El tipo específico se determina por cuál constructor se llama. No hay condicionales. El polimorfismo se resuelve en tiempo de ejecución.

### (6) README con Diagrama Mermaid

El `README.md` en la raíz incluye:
- Descripción del proyecto
- Arquitectura (jerarquía de clases en Mermaid)
- Endpoints disponibles
- Conceptos implementados (abstracción, herencia, polimorfismo, encapsulamiento)
- Instrucciones de ejecución
- Tecnologías usadas (Java 21, Spring Boot 4.1.1, Maven)

Diagrama Mermaid muestra:
- Clase `Arma` (padre abstracto)
- Clases `ArmaDeFuego` y `Granada` (intermedias)
- Relaciones de herencia (`<|--`)
- Subclases concretas de ambas ramas

---

## Criterios de Aceptación

### Encapsulamiento
- ✅ Atributos `protected` en clases base, no `public`
- ✅ Getters/setters controlados (aunque el taller no requiere validación, se respeta el patrón)
- ✅ Estado no se corrompe desde el controller

### Ocultamiento
- ✅ El método abstracto no es un "getter de todos los campos"
- ✅ El comportamiento de cada arma está oculto en la clase, no expuesto en URLs
- ✅ El controller no accede directamente a campos internos

### Polimorfismo
- ✅ Método abstracto `getComportamiento()` en la clase padre
- ✅ Dos ramas independientes (`ArmaDeFuego`, `Granada`) lo implementan distinto
- ✅ Nietas especializan aún más el comportamiento
- ✅ Variables de tipo padre en el controller, instancias reales variables
- ✅ Sin `if` para determinar qué hacer según el tipo

### Compilación y Ejecución
- ✅ `./mvnw -q compile` sin errores
- ✅ `./mvnw spring-boot:run` arranca en puerto 8080
- ✅ `curl http://localhost:8080/` devuelve la confirmación
- ✅ `curl http://localhost:8080/arma/pistola` devuelve JSON con comportamiento polimórfico

### Control de Versiones
- ✅ Git status: repositorio limpio
- ✅ Commits con mensajes claros y semánticos
- ✅ Historia legible en `git log`
- ✅ Código pusheado a GitHub

---

## Cómo Probar

### 1. Compilar
```bash
cd LT-taller-git-2026
./mvnw -q compile
```
Debe terminar sin errores.

### 2. Ejecutar
```bash
./mvnw spring-boot:run
```
La app debe estar lista en `http://localhost:8080` en 10-20 segundos.

### 3. Probar Endpoints (en otra terminal)

**Index:**
```bash
curl http://localhost:8080/
# Devuelve: API REST de Armas de Counter-Strike 2
```

**Crear Pistola (valores por defecto):**
```bash
curl http://localhost:8080/arma/pistola
# JSON con: comportamiento = "Dispara con precision 0.75 y retroceso 0.3."
```

**Crear Pistola (parámetros personalizados):**
```bash
curl "http://localhost:8080/arma/pistola?danio=50&precision=0.9"
# JSON con: danio = 50, comportamiento con precision = 0.9
```

**Crear Rifle:**
```bash
curl http://localhost:8080/arma/rifle
# JSON con: comportamiento = "Dispara con precision 0.85 y retroceso 0.75."
```

**Crear Granada Flash:**
```bash
curl http://localhost:8080/arma/granada-flash
# JSON con: comportamiento = "Destella con intensidad 3.0 a 15.0m, cegando a enemigos en radio 25.0m."
```

**Crear Granada Humo:**
```bash
curl http://localhost:8080/arma/granada-humo
# JSON con: comportamiento = "Crea cortina de humo durante 4.5s en radio 20.0m, ocultando visibilidad a 18.0m."
```

**Crear Granada Detonadora:**
```bash
curl http://localhost:8080/arma/granada-detonadora
# JSON con: comportamiento = "Explota con dano 60 en radio 18.0m, aturdiendo 2.0s y causando visibilidad 100.0."
```

**Crear Subfusil:**
```bash
curl http://localhost:8080/arma/subfusil
# JSON con: comportamiento = "Dispara con precision 0.70 y retroceso 0.5."
```

### 3.b Trabajar con Jugador e Inventario

**Crear Jugador:**
```bash
curl -X POST http://localhost:8080/jugador \
  -H "Content-Type: application/json" \
  -d '{"nombre": "Player1"}'
# Devuelve: {"id": "1", "nombre": "Player1", "inventario": []}
```

**Agregar Arma al Inventario:**
```bash
curl -X POST http://localhost:8080/jugador/1/agregar-arma \
  -H "Content-Type: application/json" \
  -d '{"danio": 25, "precio": 500, "equipo": "T", "peso": 1.5, "precision": 0.75, "balasCargador": 12, "cargadores": 3, "retroceso": 0.3, "tiempoRecarga": 0.5, "animacion": "pistol_fire"}'
```

**Obtener Jugador con Inventario:**
```bash
curl http://localhost:8080/jugador/1
# Devuelve: {
#   "id": "1",
#   "nombre": "Player1",
#   "inventario": [
#     {"danio": 25, "comportamiento": "Dispara con precision 0.75 y retroceso 0.3.", ...}
#   ]
# }
```

**Disparar con Todas las Armas:**
```bash
curl -X POST http://localhost:8080/jugador/1/disparar
# Resultado: Cada ArmaDeFuego en el inventario dispara (salida en consola)
```

**Recargar Todas las Armas:**
```bash
curl -X POST http://localhost:8080/jugador/1/recargar
# Resultado: Cada ArmaDeFuego en el inventario recarga
```

**Lanzar Todas las Granadas:**
```bash
curl -X POST http://localhost:8080/jugador/1/lanzar-granadas
# Resultado: Cada Granada en el inventario se lanza (salida en consola)
```

### 4. Verificar en git

```bash
git log
# Debe mostrar commits semánticos (feat: ..., fix: ..., etc.)

git status
# Debe estar limpio (nothing to commit)
```

---

## Conceptos Demostrados

1. **Abstracción**
   - Clase `Arma` define un contrato (método abstracto `getComportamiento()`)
   - No se puede instanciar directamente; solo sus hijas concretas
   - `Granada` también es abstracta, fuerza a sus hijas a especializarse

2. **Herencia**
   - `ArmaDeFuego` hereda atributos y métodos de `Arma`
   - `Pistola`, `Rifle`, `Subfusil`, `Francotirador` heredan de `ArmaDeFuego`
   - `GranaFlash`, `GranaHumo`, `GranaDetonadora` heredan de `Granada`
   - Código reutilizable sin duplicación

3. **Polimorfismo**
   - El método `getComportamiento()` se ejecuta según la clase real, no la declarada
   - El controller ve `Arma` (tipo padre) pero el método que se ejecuta es de `Pistola`, `GranaFlash`, etc.
   - Permite tratar "cualquier arma" sin distinguir el tipo

4. **Encapsulamiento**
   - Atributos `protected` en clases base
   - Métodos públicos (getters) para acceso controlado
   - El controller no manipula campos directamente
   - La lógica vive en las clases, no en HTTP

5. **Separación de Capas**
   - `cs2/` — lógica del dominio (qué es un arma, cómo se comporta)
   - `web/` — HTTP (cómo conversar con el mundo exterior)
   - El controller NO sabe cómo se comporta el arma; solo pide `getComportamiento()`

---

## Entrega

- **Código:** GitHub (`https://github.com/LTP22/ltauber-tp-lp3-2026`)
- **Este archivo:** Adjuntado en Classroom como `ESPECIFICACIONES.md` (Markdown)

---

**Alumno:** Luis Tauber (LTP22)  
**Fecha:** 2026-09-25  
**Facultad:** Facultad de Ciencias y Tecnología  
**Licencia:** Apache 2.0
