import java.io.BufferedReader;

public class ReceptorMensajes extends Thread {
    BufferedReader lector;

    public ReceptorMensajes(BufferedReader in) {
        this.lector = in;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = lector.readLine()) != null) {
                System.out.println(message);
            }
            System.out.println("Servidor sin conexión.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Juego finalizado.");
            System.exit(0);
        }
    }
}