package pc2_BattleCity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfazGrafica extends JFrame {
    GameBoardCanvas gameBoardCanvas;
    public static final int HEIGHT=800;
    public static final int WIDTH=800;
    public static final int GRIDSIZE=20;
    private int NIVEL=1;

    Juego juego;
    private Mapa mapa;
    private Tanque[] jugadores;
    private Enemigo[] enemigos;
    private JLabel[] lblVidaJugadores;
    private JLabel[] lblPuntajeJugadores;
    private JLabel[] lblVidaEnemigos;
    private JLabel[] lblPuntajeEnemigos;



    public InterfazGrafica() {
        this.juego = new Juego();
        this.gameBoardCanvas = new GameBoardCanvas();

        gameBoardCanvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.add(gameBoardCanvas);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setResizable(false);
        this.setTitle("BATTLECITY");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear objetos
        // ...

        // Configurar paneles
        // ...

        // Añadir paneles a la ventana
        // ...

        // Iniciar el juego
        // ...
    }

    class GameBoardCanvas extends JPanel{
        ImagePanel p[] = new ImagePanel[3];

        int numTanques = 3;

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            crearObjetos(g);
            dibujaTanques();
            setBackground(new Color(4,6,46));
            for(int i=0;i<numTanques;++i){
                p[i].paintComponent(g);
            }
        }
        public void dibujaTanques(){
            p[0]=new ImagePanel(2,2);
            p[1]= new ImagePanel(2, juego.mapa.getAlto()-5);
            p[2]= new ImagePanel(juego.mapa.getAncho()-5, 2);
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
        // Mover jugador en la dirección indicada
        // ...
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
        private BufferedImage image;
        Image img;

        public ImagePanel(int x,int y) {
            this.x = x*GRIDSIZE;
            this.y=y*GRIDSIZE;
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
            System.out.println("Pintando");
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(3*Math.PI/2, x + 3*GRIDSIZE/2,y + 3*GRIDSIZE/2);
            g2d.drawImage(img, x, y,null); // see javadoc for more info on the parameters
        }

        public void setPosition(int x, int y){
            this.x=x*GRIDSIZE;this.y=y*GRIDSIZE;
        }

    }
}
