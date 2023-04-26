package pc2_BattleCity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazGrafica extends JFrame implements KeyListener {
    GameBoardCanvas gameBoardCanvas;
    public static final int HEIGHT=600;
    public static final int WIDTH=600;
    public static final int GRIDSIZE=15;
    private int NIVEL=1;


    Juego juego;



    public InterfazGrafica() {
        this.juego = new Juego();
        this.gameBoardCanvas = new GameBoardCanvas();

        gameBoardCanvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gameBoardCanvas.p[0]=new ImagePanel(0);
        gameBoardCanvas.e = new EaglePanel();
        this.add(gameBoardCanvas);
        this.setPreferredSize(new Dimension(WIDTH + 10, HEIGHT + 40));
        this.pack();
        this.setResizable(false);
        this.setTitle("BATTLECITY");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);

        // Crear objetos
        // ...

        // Configurar paneles
        // ...

        // Añadir paneles a la ventana
        // ...

        // Iniciar el juego
        // ...
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Mueve el tanque según la tecla presionada
        switch (key) {
            case KeyEvent.VK_W:
                moverJugador(0, Direccion.ARRIBA);
                break;
            case KeyEvent.VK_A:
                moverJugador(0, Direccion.IZQUIERDA);
                break;
            case KeyEvent.VK_S:
                moverJugador(0, Direccion.ABAJO);
                break;
            case KeyEvent.VK_D:
                moverJugador(0, Direccion.DERECHA);
                break;
        }

        // Actualiza la posición del JLabel del tanque
        gameBoardCanvas.p[0].setPosition(juego.getTanque(0).getX(), juego.getTanque(0).getY());
        gameBoardCanvas.repaint();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    class GameBoardCanvas extends JPanel{
        ImagePanel p[] = new ImagePanel[3];
        EaglePanel e;
        int numTanques = 1;
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            crearObjetos(g);
            setBackground(new Color(4,6,46));
            e.paintComponent(g);
            for(int i=0;i<numTanques;++i){
                p[i].paintComponent(g);
            }

        }

    }



    private void dibujaMetal(int x, int y,Graphics g2d){
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x*GRIDSIZE, y*GRIDSIZE,GRIDSIZE, GRIDSIZE);
        g2d.setColor(new Color(92,92,92));
        g2d.fillRect(x*GRIDSIZE, y*GRIDSIZE,GRIDSIZE, GRIDSIZE);
    }

    private void dibujaLadrillo(int x, int y, Graphics g2d){
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x*GRIDSIZE, y*GRIDSIZE,GRIDSIZE, GRIDSIZE);
        g2d.setColor(Color.getHSBColor(25, 0.7f,0.59f));
        g2d.fillRect(x*GRIDSIZE, y*GRIDSIZE,GRIDSIZE, GRIDSIZE);
    }


    private void crearObjetos(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for(int j=0; j<this.juego.mapa.getAlto(); ++j){
            for(int i=0; i<this.juego.mapa.getAncho(); ++i){

                if(this.juego.mapa.getCasilla(i,j) == 1){
                    dibujaMetal(i,j,g2d);
                }
                else if(this.juego.mapa.getCasilla(i,j)==2){
                    dibujaLadrillo(i,j,g2d);
                }
            }
        }


        // Crear jugadores
        // ...

        // Crear enemigos
        // ...

        // Crear etiquetas para mostrar información de los jugadores
        // ...

        // Crear etiquetas para mostrar información de los enemigos
        // ...
    }

    private void configurarPaneles() {
        // Configurar panel del mapa
        // ...

        // Configurar panel de información
        // ...
    }

    private void añadirPaneles() {
        // Añadir panel del mapa
        // ...

        // Añadir panel de información
        // ...
    }

    private void iniciarJuego() {
        // Iniciar ciclo del juego
        // ...
    }

    private void actualizar() {
        // Actualizar posiciones de los objetos
        // ...

        // Actualizar la interfaz gráfica
        // ...
    }

    private void moverJugador(int jugador, Direccion direccion) {
        Tanque t = juego.getTanque(jugador);
        if(t.getDireccion()!=direccion){
            t.setDireccion(direccion);
            gameBoardCanvas.repaint();
            return;
        }
        int x = t.getX(), y=t.getY();
        if(direccion== Direccion.ARRIBA){
            for(int i=0; i<3;i++){
                if(juego.mapa.getCasilla(x+i, y-1)!=0){
                    return;
                }
            }
        }
        else if(direccion==Direccion.DERECHA){
            for(int i=0; i<3;i++){
                if(juego.mapa.getCasilla(x+3, y+i)!=0){
                    return;
                }
            }
        }
        else if(direccion==Direccion.ABAJO){
            for(int i=0; i<3;i++){
                if(juego.mapa.getCasilla(x+i, y+3)!=0){
                    return;
                }
            }
        }
        else if(direccion==Direccion.IZQUIERDA){
            for(int i=0; i<3;i++){
                if(juego.mapa.getCasilla(x-1, y+i)!=0){
                    return;
                }
            }
        }
        t.mover(direccion);
    }

    private void dispararJugador(int jugador) {
        // Crear bala del jugador
        // ...
    }

    private void actualizarBalas() {
        // Actualizar posiciones de las balas
        // ...

        // Comprobar colisiones de las balas
        // ...
    }

    private void moverEnemigos() {
        // Mover enemigos en dirección aleatoria
        // ...
    }

    private void dispararEnemigos() {
        // Crear balas de los enemigos
        // ...
    }

    private void actualizarVidaJugadores() {
        // Actualizar etiquetas de vida de los jugadores
        // ...
    }

    private void actualizarPuntajeJugadores() {
        // Actualizar etiquetas de puntaje de los jugadores
        // ...
    }

    private void actualizarVidaEnemigos() {
        // Actualizar etiquetas de vida de los enemigos
        // ...
    }

    private void actualizarPuntajeEnemigos() {
        // Actualizar etiquetas de puntaje de los enemigos
        // ...
    }

    public class ImagePanel extends JPanel{
        int x=0,y=0;
        Tanque t;
        int direccion=0;
        private BufferedImage image;
        Image img;

        public ImagePanel(int jugador) {
            this.t = juego.getTanque(jugador);
            this.x = t.getX()*GRIDSIZE;
            this.y = t.getY()*GRIDSIZE;
            try {
                image = ImageIO.read(new File("src/pc2_BattleCity/tank.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(3*GRIDSIZE, 3*GRIDSIZE,Image.SCALE_SMOOTH));
                img = icon.getImage();
            } catch (IOException ex) {
                System.out.println("No se encontró imagen");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            System.out.println(x/GRIDSIZE+ " "+y/GRIDSIZE);
            Graphics2D g2d = (Graphics2D) g;
            System.out.println(t.getDireccion());
            if(t.getDireccion()==Direccion.DERECHA){
                g2d.rotate(Math.PI/2, x + 3*GRIDSIZE/2,y + 3*GRIDSIZE/2);
            }
            else if(t.getDireccion()==Direccion.IZQUIERDA){
                g2d.rotate(-Math.PI/2, x + 3*GRIDSIZE/2,y + 3*GRIDSIZE/2);
            }
            else if(t.getDireccion()==Direccion.ABAJO ){
                g2d.rotate(2*Math.PI/2, x + 3*GRIDSIZE/2,y + 3*GRIDSIZE/2);
            }


            g2d.drawImage(img, t.getX()*GRIDSIZE, t.getY()*GRIDSIZE,null); // see javadoc for more info on the parameters
        }

        public void setPosition(int x, int y){

            this.x=x*GRIDSIZE;this.y=y*GRIDSIZE;
            repaint();
        }

    }

    public class EaglePanel extends JPanel{
        int x=18,y=34;
        private BufferedImage image;
        Image img;

        public EaglePanel() {
            try {
                image = ImageIO.read(new File("src/pc2_BattleCity/eagle.png"));
                ImageIcon icon = new ImageIcon(image.getScaledInstance(4*GRIDSIZE, 4*GRIDSIZE,Image.SCALE_SMOOTH));
                img = icon.getImage();
            } catch (IOException ex) {
                System.out.println("No se encontró imagen");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(img, x*GRIDSIZE, y*GRIDSIZE,null); // see javadoc for more info on the parameters
        }

    }

    public static void main(String[] args) {
        new InterfazGrafica();
    }
}
