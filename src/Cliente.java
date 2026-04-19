import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 1234);
                PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner sc = new Scanner(System.in);
        ) {
            new ReceptorMensajes(lector).start();
            while(true) {
                String respuesta = sc.nextLine();
                escritor.println(respuesta);
            }

        } catch (Exception e) {
            System.err.println("Desconectado del servidor.");
        }
    }
}
