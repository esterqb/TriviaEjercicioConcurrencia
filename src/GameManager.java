import java.util.ArrayList;

public class GameManager {
    private ArrayList<ClientHandler> clientes;
    private static volatile boolean rondaAbierta = false;
    private ArrayList<Pregunta> preguntas = new ArrayList<>();

    public GameManager(ArrayList<ClientHandler> clientes) {
        this.clientes = clientes;
        preguntas.add(new Pregunta("¿Por cuánto compró la Iglesia Católica la Mezquita de Córdoba?", "1.569.300€", "2.569.300€", "569.200'59€", "30€", "d"));
        preguntas.add(new Pregunta("¿En qué año llegó el hombre a la luna?", "1969", "1959", "1975", "1980", "a"));
        preguntas.add(new Pregunta("¿De qué color es el cielo?", "Azul.", "Violeta por la dispersión de Rayleigh, pero la sensibilidad de nuestros fotorreceptores (conos) nos hace percibirlo azul.", "Blanco por la dispersión de Mie, pero el campo magnético de la Tierra polariza los fotones al espectro azul.", "Negro, y lo que vemos es la radiación electromagnética corrida al azul por la rotación del planeta.", "b"));
        preguntas.add(new Pregunta("¿Cuál es la complejidad temporal exacta del algoritmo de Held-Karp basado en programación dinámica para el Problema del Viajante de Comercio (TSP)?", "O(n!)", "O(n^2 * 2^n)", "O(n * 2^n)", "O(n^3)", "b"));
        preguntas.add(new Pregunta("¿Cuál es la solución exacta a las ecuaciones de campo de Einstein que describe un agujero negro en rotación y sin carga?", "Métrica de Schwarzschild", "Métrica de Reissner-Nordström", "Métrica de Alcubierre", "Métrica de Kerr", "d"));
        preguntas.add(new Pregunta("En el árbol sintáctico de la oración 'El manuscrito de Saussure revolucionó la lingüística', ¿cuál es el dominio de mando-c exacto del Sintagma Determinante (DP) 'Saussure'?", "Manda-c al VP 'revolucionó...' por precedencia lineal en la estructura superficial.", "Manda-c al núcleo N 'manuscrito' por asignación de Caso genitivo.", "Manda-c a la preposición 'de', pero a ningún otro elemento fuera de ese Sintagma Preposicional (PP).", "Manda-c simétricamente a todo el Tense Phrase (TP) por ser parte del sujeto lógico.", "c"));
        preguntas.add(new Pregunta("¿Quiénes son novios en realidad?", "Jaime y Óscar", "Manu y Rafa", "JD y Xenon", "Manu y Xenon", "b"));
    }

    public static boolean isRondaAbierta() { return rondaAbierta; }

    public void iniciarPartida() {
        try {
            enviarTodos("\nLA PARTIDA COMIENZA EN 3 SEGUNDOS");
            Thread.sleep(3000);

            int numPregunta = 1;
            for (Pregunta p : preguntas) {
                enviarTodos("\nPREGUNTA "+ numPregunta);
                enviarTodos(p.getEnunciado());
                enviarTodos("A) " + p.getRespuestaA() + " | B) " + p.getRespuestaB() + " | C) " + p.getRespuestaC() + " | D) " + p.getRespuestaD());
                enviarTodos("Introduce tu respuesta (pon a, b, c o d). Tienes 40 segundos.");
                rondaAbierta = true;
                Thread.sleep(40000);
                rondaAbierta = false;

                enviarTodos("\nSE ACABÓ!!!");
                for (ClientHandler cl : clientes) {
                    cl.corregirRespuesta(p.getRespuestaCorrecta());
                    cl.limpiaRespuesta();
                }
                mostrarRanking(false);
                numPregunta++;
                Thread.sleep(4000);
            }
            enviarTodos("\nFIN DEL JUEGO!!!");
            mostrarRanking(true);

        } catch (Exception e) {
            System.out.println("Error en la partida: " + e.getMessage());
        }
    }

    public void enviarTodos(String msg) {
        for (ClientHandler cl : clientes) {
            cl.enviarMensaje(msg);
        }
    }

    public void mostrarRanking(boolean esFinal) {
        String titulo = esFinal ? "RANKING FINAL" : "RANKING ACTUAL";
        enviarTodos(titulo);
        System.out.println("\n" + titulo);
        String ganador = "";
        int maxPuntos = -1;

        for (ClientHandler client : clientes) {
            String linea = client.getNick() + " -> " + client.getPuntos() + " puntos";
            enviarTodos(linea);
            System.out.println(linea);

            if (client.getPuntos() > maxPuntos) {
                maxPuntos = client.getPuntos();
                ganador = client.getNick();
            }
        }

        if (esFinal) {
            enviarTodos("HA GANADO: " + ganador + " CON " + maxPuntos + " PUNTOS");
        }
    }
}
