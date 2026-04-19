# Cómo jugar a este Trivia

Primero, debes hacer git clone del proyecto:
- Ve al directorio donde quieras pegar el proyecto, por ejemplo a Documentos.
- Ahí, haces clic en el botón secundario y le das a Abrir en Terminal.
- En la terminal, vas a pegar: git clone https://github.com/esterqb/TriviaEjercicioConcurrencia

Ahora, vas a abrir tu IDE, por ejemplo, IntelliJ IDEA. Aquí, vas a abrir el proyecto y en la carpeta src encontrarás las diferentes clases.
Tienes que ejecutar la clase Servidor primero y luego la clase Cliente.
Una vez hagas eso, el orden será el siguiente:

1. SERVIDOR DIRÁ: SERVIDOR TRIVIA INICIADO. Esperando jugadores... Escribe START cuando quieras empezar.
2. CLIENTES DIRÁ: Bienvenido al Trivia. Introduce tu nombre:
 - Aquí, introducirás tu nombre de usuario para la partida, por ejemplo, Ester.
3. CLIENTE DIRÁ: Hola "tu nombre", cargando partida...
4. SERVIDOR DIRÁ: Nuevo jugador conectado. Total: 1/10. 
5. Ahora, puedes escribir en el servidor "start" para comenzar la partida.
6. Ahora, CLIENTES dirá: LA PARTIDA COMIENZA EN 3 SEGUNDOS.
7. Luego, en clientes se lanzarán las preguntas por orden.
8. Se debe responder con la letra de la opción que creamos que es correcta, por ejemplo, "a".
9. Al pasar 40 segundos, se cierra la pregunta actual y se salta a la siguiente. Luego, se te dirá si es correcta o no.
10. Tras 4 segundos, aparece la pregunta siguiente.
11. Al finalizar, se muestra el ranking de los jugadores con los puntos.