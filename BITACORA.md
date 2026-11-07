# Bitácora de asistencia de IA

**Alumno:** Luis Tauber (LTP22)
**Repositorio:** https://github.com/LTP22/ltauber-tp-lp3-2026

## Herramienta

- **Marca:** Claude Code (Anthropic), en la terminal.
- **Modelo de LLM:**
  - `claude-sonnet-5` (Sonnet 5): sesión de POO-08 (CRUD, H2, excepciones, interfaz e Insomnia) y la sesión anterior de sobrecarga/sobreescritura, reorganización en `domain`/`rest.controller`, documentación y bitácora.
  - `claude-haiku-4-5-20251001` (Haiku 4.5): sesiones anteriores del taller, en las que se creó el esqueleto Spring Boot, las clases de CS2 y la primera versión de la API.

## Resumen de prompts

Sesiones anteriores (Haiku 4.5):
- Pedí revisar el estado del taller y armar la API de CS2 con herencia, método abstracto y polimorfismo, siguiendo las consignas del profesor.
- Pregunté conceptos básicos (qué es Spring Boot, para qué sirve un controller, qué hace `git checkout`) y pedí explicaciones con analogías.
- Pedí configurar la colaboración con un compañero: cada uno contribuye en el repo del otro mediante una rama y un pull request.
- Pedí agregar las clases Tienda y Jugador, la documentación del README con diagrama Mermaid y el documento de especificaciones.

Sesión de la entrega de sobrecarga y sobreescritura (Sonnet 5):
- Pedí reorganizar los paquetes según el template de la cátedra (`domain` y `rest.controller`).
- Pedí que los constructores y los mensajes de las clases validen sus reglas, que los controllers informen los errores y que se agregue una sobrecarga de disparo.
- Pedí revisar el repositorio contra el enunciado y la rúbrica, y completar lo que faltaba: bitácora, README, especificaciones y pruebas.
- Pedí corregir la documentación cuando no coincidía con el código (por ejemplo, los parámetros de los endpoints de jugador).

Sesión de corrección cruzada y rúbrica oficial (Sonnet 5):
- Pedí armar un documento Word/PDF con la autocorrección del propio TP contra la rúbrica oficial de 8 criterios, con evidencia real (tests, curl, Git).
- Aclaré que la corrección era cruzada entre compañeros, no autocorrección: pedí revisar el repositorio de un compañero (Amir Dujak) contra la misma rúbrica, con evidencia de lo que funcionaba y lo que no.
- Pedí capturas de pantalla reales como evidencia en vez de bloques de texto, y que el informe fuera más corto.
- Pedí verificar un reclamo del compañero ("no compila"): se clonó su repo limpio y se confirmó que sí compilaba; se encontró que `mvnw` estaba commiteado sin el bit de ejecución (problema real en Mac/Linux, no en Windows) y se corrigió.

Sesión de POO-08 — CRUD, H2, excepciones e Insomnia (Sonnet 5):
- Pasé el enunciado nuevo (persistencia en H2, CRUD, dos reglas de negocio con simulación persistida, jerarquía de excepciones, interfaz de dominio, colección de Insomnia) y pedí ponerse al día con todos los criterios de la rúbrica.
- Pedí leer también el repositorio de GitHub de la cátedra (enunciado y template de paquetes) para seguir exactamente la convención esperada (capa `service`/`service.impl` entre el controller y el repositorio).
- La IA decidió, y yo aprobé con el resultado, persistir la familia `Arma`/`ArmaDeFuego`/`Granada` (el mismo método abstracto de POO-06) en vez de una familia nueva, para no duplicar conceptos ya probados.

## Qué hice yo y qué hizo la IA

- La IA escribió la mayor parte del código y de la documentación a partir de mis pedidos. Revisé el resultado, ejecuté la aplicación y probé los endpoints con `curl` e Insomnia.
- Las especificaciones y el README incluyen correcciones posteriores: algunos ejemplos que la IA escribió primero no coincidían con el código y se corrigieron con pruebas reales.
- En la sesión de POO-08, la IA probó cada endpoint nuevo contra el servicio real antes de dar nada por terminado, y encontró y corrigió un bug propio en el momento (un `POST` inválido devolvía 500 en vez de 400 porque al controller CRUD nuevo le faltaba el handler de `IllegalArgumentException` que sí tenían los controllers viejos).
