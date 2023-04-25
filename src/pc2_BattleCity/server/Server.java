package pc2_BattleCity.server;

import pc2_BattleCity.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Server {
    private ServerSocket servidorSocket;
    private ArrayList<Client> clientesConectados;
    private Mapa mapa;
    private ArrayList<Tanque> tanques;
    private ArrayList<Bala> balas;
    private ArrayList<Enemigo> enemigos;
    private int puerto;

    public Server(int puerto) {
        this.puerto = puerto;
        this.clientesConectados = new ArrayList<>();
        //this.mapa = new Mapa(new int[100][100]);
        this.tanques = new ArrayList<>();
        this.balas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
    }

    public void iniciarServidor() {
        try {
            servidorSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto: " + puerto);

            while (true) {
                Socket socketCliente = servidorSocket.accept();
                BlockingQueue<Mensaje> colaMensajes = new LinkedBlockingQueue<>();
                Client cliente = new Client(socketCliente, colaMensajes );
                clientesConectados.add(cliente);
                System.out.println("Nuevo cliente conectado desde la dirección: " + socketCliente.getInetAddress());

                // Enviamos el mapa y los objetos iniciales al cliente
                cliente.enviarMapa(mapa);
                cliente.enviarTanques(tanques);
                cliente.enviarEnemigos(enemigos);
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    public void recibirMovimiento(Tanque tanque) {
        // Actualizamos la posición del tanque
        for (Tanque t : tanques) {
            if (t.equals(tanque)) {
                t.setX(tanque.getX());
                t.setY(tanque.getY());
                break;
            }
        }

        // Enviamos la actualización a todos los clientes
        for (Client cliente : clientesConectados) {
            cliente.enviarTanques(tanques);
        }
    }

    public void recibirDisparo(Bala bala) {
        balas.add(bala);

        // Enviamos la actualización a todos los clientes
        for (Client cliente : clientesConectados) {
            cliente.enviarBalas(balas);
        }
    }

    public void actualizar() {
        // Actualizamos la lógica del juego (movimiento de enemigos, colisiones, etc.)
        // ...

        // Enviamos la actualización a todos los clientes
        for (Client cliente : clientesConectados) {
            cliente.enviarTanques(tanques);
            cliente.enviarBalas(balas);
            cliente.enviarEnemigos(enemigos);
        }
    }

    public static void main(String[] args) {
        Server servidor = new Server(5000);
        servidor.iniciarServidor();
    }
}
