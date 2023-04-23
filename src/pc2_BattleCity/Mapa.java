package pc2_BattleCity;

public class Mapa {
    private int[][] casillas;
    private int ancho;
    private int alto;

    public Mapa(int[][] casillas) {
        this.casillas = casillas;
        this.ancho = casillas.length;
        this.alto = casillas[0].length;
    }

    public int getCasilla(int x, int y) {
        return casillas[x][y];
    }

    public boolean esMuroNoDestruible(int x, int y) {
        return casillas[x][y] == 1;
    }

    public boolean esMuroDestruible(int x, int y) {
        return casillas[x][y] == 2;
    }

    public void destruirMuro(int x, int y) {
        casillas[x][y] = 0;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}

