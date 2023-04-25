package pc2_BattleCity;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private int numJugadores=1;
    public Mapa mapa;
    private List<Tanque> tanques;
    private List<Bala> balas;
    private List<Enemigo> enemigos;

    // Constructor
    public Juego() {
        // Crear el mapa, los tanques, las balas y los enemigos
        this.mapa = new Mapa(1);
        this.tanques = new ArrayList<>();
        this.balas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        crearTanques();
        crearEnemigos();
    }

    // Método para crear los tanques
    private void crearTanques() {
        // Crear los tanques y agregarlos a la lista de tanques
        /*Tanque tanque1 = new Tanque(0, 0, Direccion.ARRIBA, 1);
        Tanque tanque2 = new Tanque(mapa.getAncho() - 1, mapa.getAlto() - 1, Direccion.ABAJO, 1);
        tanques.add(tanque1);
        tanques.add(tanque2);*/
        for(int i=0; i<numJugadores; ++i){
            Tanque t = new Tanque(2,2,Direccion.ABAJO, 1);
            tanques.add(t);
        }
    }

    // Método para crear los enemigos
    private void crearEnemigos() {
        // Crear los enemigos y agregarlos a la lista de enemigos
        Enemigo enemigo1 = new Enemigo(5, 5, Direccion.IZQUIERDA);
        Enemigo enemigo2 = new Enemigo(10, 10, Direccion.DERECHA);
        enemigos.add(enemigo1);
        enemigos.add(enemigo2);
    }

    // Método para mover los tanques
    public void moverTanques(int indiceTanque, Direccion direccion) {
        // Mover el tanque con el índice dado en la dirección dada
        tanques.get(indiceTanque).mover(direccion);
    }

    // Método para disparar una bala desde un tanque
    public void disparar(int indiceTanque) {
        // Crear una nueva bala en la posición del tanque y con la dirección del cañón
        Tanque tanque = tanques.get(indiceTanque);
        Bala bala = new Bala(tanque.getX(), tanque.getY(), tanque.getDireccionCanon(), 1);
        balas.add(bala);
    }

    // Método para mover las balas y detectar colisiones
    // Método para mover las balas y detectar colisiones
    public void actualizarBalas() {
        // Mover cada bala y comprobar si choca con un tanque o un enemigo
        for (Bala bala : balas) {
            bala.mover();

            // Comprobar si la bala choca con un tanque
            for (Tanque tanque : tanques) {
                if (bala.getX() == tanque.getX() && bala.getY() == tanque.getY()) {
                    // La bala chocó con un tanque
                    tanque.recibirDanio(bala.getPotencia());
                    balas.remove(bala);
                    return;
                }
            }

            // Comprobar si la bala choca con un enemigo
            for (Enemigo enemigo : enemigos) {
                if (bala.getX() == enemigo.getX() && bala.getY() == enemigo.getY()) {
                    // La bala chocó con un enemigo
                    enemigo.recibirDanio(bala.getPotencia());
                    balas.remove(bala);
                    return;
                }
            }

            // Comprobar si la bala choca con un muro destruible
            if (mapa.esMuroDestruible(bala.getX(), bala.getY())) {
                mapa.destruirMuro(bala.getX(), bala.getY());
                balas.remove(bala);
                return;
            }

            // Comprobar si la bala choca con un muro no destruible
            if (mapa.esMuroNoDestruible(bala.getX(), bala.getY())) {
                balas.remove(bala);
                return;
            }

            // Comprobar si la bala sale del mapa
            if (bala.getX() < 0 || bala.getX() >= mapa.getAncho() || bala.getY() < 0 || bala.getY() >= mapa.getAlto()) {
                balas.remove(bala);
                return;
            }
        }
    }

    public int getNumTanques(){
        return tanques.size();
    }
    public Tanque getTanque(int index){
        return tanques.get(index);
    }

}
