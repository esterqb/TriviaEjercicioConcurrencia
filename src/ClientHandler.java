import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket cliente;
    private BufferedReader in;
    private PrintWriter out;

    private String nick;
    private int puntos;
    private boolean haRespondido;
    private String respuestaActual;

    public ClientHandler(Socket socket) {
        this.cliente = socket;
        this.puntos = 0;
        this.haRespondido = false;
        this.respuestaActual = null;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            out = new PrintWriter(cliente.getOutputStream(), true);

            out.println("Bienvenido al Trivia. Introduce tu nombre:");
            this.nick = in.readLine();
            out.println("Hola " +nick +", cargando partida...");

            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                if (GameManager.isRondaAbierta() && !haRespondido) {
                    String letra = mensaje.trim().toLowerCase();
                    if (letra.equals("a") || letra.equals("b") || letra.equals("c") || letra.equals("d")) {
                        this.respuestaActual = letra;
                        this.haRespondido = true;
                        out.println("Has respondido " + letra.toUpperCase());
                    } else {
                        out.println("Escribe solo A, B, C o D.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(nick + " ha abandonado la partida.");
        }
    }

    public void enviarMensaje(String msg) {
        out.println(msg);
    }

    public void limpiaRespuesta() {
        this.haRespondido = false;
        this.respuestaActual = null;
    }

    public void corregirRespuesta(String sol) {
        if (respuestaActual != null && respuestaActual.equals(sol.toLowerCase())) {
            puntos += 1;
            enviarMensaje("Respuesta correcta, +1p.");
        } else if (respuestaActual == null) {
            enviarMensaje("Tiempo agotado. Sin respuesta.");
        } else {
            enviarMensaje("Respuesta incorrecta. La respuesta era: " + sol.toUpperCase());
        }
    }

    public String getNick() { return nick; }
    public int getPuntos() { return puntos; }
}