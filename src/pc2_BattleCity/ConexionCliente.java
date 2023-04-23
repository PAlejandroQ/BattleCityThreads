package pc2_BattleCity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class ConexionCliente implements Runnable {
    private Socket socketCliente;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private BlockingQueue<Mensaje> colaMensajes;

    public ConexionCliente(Socket socketCliente, BlockingQueue<Mensaje> colaMensajes) throws IOException {
        this.socketCliente = socketCliente;
        this.entrada = new ObjectInputStream(socketCliente.getInputStream());
        this.salida = new ObjectOutputStream(socketCliente.getOutputStream());
        this.colaMensajes = colaMensajes;
    }

    public void enviarMensaje(Mensaje mensaje) {
        try {
            salida.writeObject(mensaje);
            salida.reset();
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje al cliente");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Esperar a recibir un mensaje del cliente
                Mensaje mensaje = (Mensaje) entrada.readObject();
                colaMensajes.put(mensaje);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            System.out.println("Error al recibir mensaje del cliente");
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexión con el cliente");
            }
        }
    }

    public void enviarMapa(Mapa mapa) {
    }

    public void enviarTanques(ArrayList<Tanque> tanques) {
    }

    public void enviarEnemigos(ArrayList<Enemigo> enemigos) {
    }

    public void enviarBalas(ArrayList<Bala> balas) {
    }
}
