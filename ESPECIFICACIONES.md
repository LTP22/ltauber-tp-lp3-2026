# Especificaciones: API REST de armas de Counter-Strike 2

**Alumno:** Luis Tauber (LTP22)
**Asignatura:** Lenguaje de Programación 3 (CYT646), ejercicio POO-06
**Repositorio:** https://github.com/LTP22/ltauber-tp-lp3-2026

---

## 1. Objetivo

Publicar un servicio HTTP con Spring Boot sobre el modelado de armas de Counter-Strike 2. El servicio demuestra:

- Herencia con una clase base abstracta y dos ramas independientes.
- Sobreescritura de un método abstracto en cada rama.
- Sobrecarga de constructores y de un mensaje del dominio.
- Ocultamiento de la información: el estado cambia solo por mensajes y los constructores rechazan valores ilegales.
- Separación entre el dominio (`domain`) y los servicios REST (`rest.controller`).

## 2. Dominio

```
Arma (abstracta)                       getComportamiento() abstracto
├── ArmaDeFuego                        sobreescribe getComportamiento()
│   ├── Pistola
│   ├── Rifle
│   ├── Subfusil
│   └── Francotirador
└── Granada (abstracta)                sobreescribe getComportamiento()
    ├── GranaFlash                     sobreescribe getComportamiento()
    ├── GranaHumo                      sobreescribe getComportamiento()
    └── GranaDetonadora                sobreescribe getComportamiento()

Jugador                                contiene List<Arma>
```

## 3. Consignas y cómo se cumplen

| # | Consigna | Dónde se cumple |
|---|---|---|
| 1 | Clases en paquetes del template | `py.edu.uc.lp3.domain`, `py.edu.uc.lp3.rest.controller`, `py.edu.uc.lp3.Application` |
| 2 | Modelado de septiembre compilando y registrado en Git | Historial de commits del repositorio |
| 3 | Método abstracto en la base y dos hijas que lo sobreescriben | `Arma.getComportamiento()` abstracto; `ArmaDeFuego` y `Granada` lo implementan |
| 4 | `GET /` y un controller que construye desde la URL | `IndexController`; `ArmaController` con `@RequestParam` |
| 5 | JSON con el comportamiento de cada hija, usando el tipo padre | Campo `comportamiento` en la respuesta de `/arma/*` |
| 6 | Constructores simples y sobrecargados; la hija llama a `super` | `Pistola(String equipo)` y `Pistola(int danio, ...)`, y lo mismo en las demás armas |
| 7 | Sobrecarga de un mensaje del dominio | `ArmaDeFuego.disparar()` y `disparar(float distancia)`; `Jugador.dispararConTodas()` y su versión con distancia |
| 8 | README con Mermaid y explicación de sobrecarga y sobreescritura | `README.md`, secciones "Arquitectura" y "Sobreescritura y sobrecarga" |
| 9 | Bitácora de IA | `BITACORA.md` |

### Qué ocurre si un valor rompe una regla

Los constructores lanzan `IllegalArgumentException`. El controller la captura y responde `400 Bad Request` con el motivo:

```
GET /arma/pistola?danio=-5
→ 400 {"error":"El daño no puede ser negativo"}

GET /arma/rifle?precision=2
→ 400 {"error":"La precision debe estar entre 0 y 1"}
```

Tampoco hay setters públicos: `balasEnCargador` cambia solo con `disparar()` y `recargar()`, y una granada solo puede lanzarse una vez.

## 4. Cómo probar

### Requisitos
- Java 21
- Git

### Pasos

```bash
git clone https://github.com/LTP22/ltauber-tp-lp3-2026.git
cd ltauber-tp-lp3-2026
./mvnw test
./mvnw spring-boot:run
```

El servicio queda disponible en `http://localhost:8080` en pocos segundos. Las pruebas unitarias (`./mvnw test`) verifican las reglas del dominio, la sobrecarga y el estado de munición y granadas.

### Verificación de endpoints

En Windows PowerShell usá `curl.exe`: el alias `curl` ejecuta `Invoke-WebRequest`, que muestra cualquier respuesta 400 como error. Un 400 es el resultado esperado en las pruebas de valores inválidos, no un fallo del servicio.

**Índice:**
```bash
curl.exe http://localhost:8080/
# API REST de Armas de Counter-Strike 2
```

**Crear un arma desde la URL (valores por defecto):**
```bash
curl.exe http://localhost:8080/arma/pistola
# "comportamiento":"Dispara con precision 0.75 y retroceso 0.3."
```

**Crear un arma con parámetros propios:**
```bash
curl.exe "http://localhost:8080/arma/pistola?danio=50&precision=0.9"
# "danio":50, "comportamiento":"Dispara con precision 0.9 y retroceso 0.3."
```

**Otras armas y granadas:**
```bash
curl.exe http://localhost:8080/arma/rifle
curl.exe http://localhost:8080/arma/subfusil
curl.exe http://localhost:8080/arma/francotirador
curl.exe http://localhost:8080/arma/granada-flash
curl.exe http://localhost:8080/arma/granada-humo
curl.exe http://localhost:8080/arma/granada-detonadora
```

**Valor inválido (debe responder 400):**
```bash
curl.exe -i "http://localhost:8080/arma/pistola?danio=-5"
```

### Flujo de jugador (sobrecarga de disparo)

```bash
# 1. Crear jugador: devuelve "id":"1"
curl.exe -X POST "http://localhost:8080/jugador?nombre=Luis"

# 2. Agregar armas al inventario
curl.exe -X POST "http://localhost:8080/jugador/1/agregar-arma?tipo=pistola"
curl.exe -X POST "http://localhost:8080/jugador/1/agregar-arma?tipo=granada-flash"

# 3. Disparar sin distancia (consume una bala) y con distancia (consume otra)
curl.exe -X POST "http://localhost:8080/jugador/1/disparar"
curl.exe -X POST "http://localhost:8080/jugador/1/disparar?distancia=50"

# 4. Ver el inventario: balasEnCargador pasa de 12 a 10
curl.exe "http://localhost:8080/jugador/1"

# 5. Recargar y lanzar granadas
curl.exe -X POST "http://localhost:8080/jugador/1/recargar"
curl.exe -X POST "http://localhost:8080/jugador/1/lanzar-granadas"   # granadasLanzadas: 1
curl.exe -X POST "http://localhost:8080/jugador/1/lanzar-granadas"   # granadasLanzadas: 0, ya fue lanzada
```

---

**Alumno:** Luis Tauber (LTP22)
**Fecha:** 2026-10-05
**Facultad:** Facultad de Ciencias y Tecnología
**Licencia:** Apache 2.0
