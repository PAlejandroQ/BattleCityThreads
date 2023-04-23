package pc2_BattleCity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Servidor {
    private ServerSocket servidorSocket;
        private ArrayList<ConexionCliente> clientesConectados;
    private Mapa mapa;
    private ArrayList<Tanque> tanques;
    private ArrayList<Bala> balas;
    private ArrayList<Enemigo> enemigos;
    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
        this.clientesConectados = new ArrayList<>();
        this.mapa = new Mapa(new int[100][100]);
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
                ConexionCliente cliente = new ConexionCliente(socketCliente, colaMensajes );
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
        for (ConexionCliente cliente : clientesConectados) {
            cliente.enviarTanques(tanques);
        }
    }

    public void recibirDisparo(Bala bala) {
        balas.add(bala);

        // Enviamos la actualización a todos los clientes
        for (ConexionCliente cliente : clientesConectados) {
            cliente.enviarBalas(balas);
        }
    }

    public void actualizar() {
        // Actualizamos la lógica del juego (movimiento de enemigos, colisiones, etc.)
        // ...

        // Enviamos la actualización a todos los clientes
        for (ConexionCliente cliente : clientesConectados) {
            cliente.enviarTanques(tanques);
            cliente.enviarBalas(balas);
            cliente.enviarEnemigos(enemigos);
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor(5000);
        servidor.iniciarServidor();
    }
}
