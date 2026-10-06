# Bitácora de asistencia de IA

**Alumno:** Luis Tauber (LTP22)
**Repositorio:** https://github.com/LTP22/ltauber-tp-lp3-2026

## Herramienta

- **Marca:** Claude Code (Anthropic), en la terminal.
- **Modelo de LLM:**
  - `claude-sonnet-5` (Sonnet 5): sesión en la que se hicieron los cambios de la entrega de sobrecarga y sobreescritura, la reorganización en `domain` y `rest.controller`, la documentación y esta bitácora.
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

## Qué hice yo y qué hizo la IA

- La IA escribió la mayor parte del código y de la documentación a partir de mis pedidos. Revisé el resultado, ejecuté la aplicación y probé los endpoints con `curl`.
- Las especificaciones y el README incluyen correcciones posteriores: algunos ejemplos que la IA escribió primero no coincidían con el código y se corrigieron con pruebas reales.
