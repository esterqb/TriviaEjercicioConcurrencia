import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {
    public static ArrayList<ClientHandler> clientes = new ArrayList<>();
    public static volatile boolean juegoIniciado = false;

    public static void main(String[] args) {
        System.out.println("SERVIDOR TRIVIA INICIADO");
        System.out.println("Esperando jugadores... Escribe START cuando quieras empezar.");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(1234);
                    while (!juegoIniciado && clientes.size() < 10) {
                        Socket cliente = server.accept();
                        ClientHandler handler = new ClientHandler(cliente);
                        handler.start();
                        clientes.add(handler);
                        System.out.println("Nuevo jugador conectado. Total: " + clientes.size() + "/10");
                    }
                } catch (Exception e) {
                    System.out.println("Recepción de clientes cerrada.");
                }
            }
        }).start();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String comando = sc.nextLine();
            if (comando.equalsIgnoreCase("START")) {
                if (clientes.isEmpty()) {
                    System.out.println("No hay jugadores conectados");
                } else {
                    juegoIniciado = true;
                    System.out.println("¡PARTIDA INICIADA!");
                    new GameManager(clientes).iniciarPartida();
                    break;
                }
            }
        }
    }
}
