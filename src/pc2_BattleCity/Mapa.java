package pc2_BattleCity;


public class Mapa {

    private int[][] casillas = new int[InterfazGrafica.WIDTH/InterfazGrafica.GRIDSIZE][InterfazGrafica.HEIGHT/InterfazGrafica.GRIDSIZE];
    private int ancho;
    private int alto;
    private int nivel;

    public Mapa(int nivel) {
        this.alto = casillas.length;
        this.ancho = casillas[0].length;
        this.nivel=nivel;
        generateMap(nivel);
    }

    public void generateMap(int nivel){
        if(nivel==1){
            this.casillas[16][ancho-3]=2;
            this.casillas[16][ancho-4]=2;
            this.casillas[16][ancho-5]=2;
            this.casillas[16][ancho-6]=2;
            this.casillas[17][ancho-3]=2;
            this.casillas[17][ancho-4]=2;
            this.casillas[17][ancho-5]=2;
            this.casillas[17][ancho-6]=2;
            this.casillas[22][ancho-3]=2;
            this.casillas[22][ancho-4]=2;
            this.casillas[22][ancho-5]=2;
            this.casillas[22][ancho-6]=2;
            this.casillas[23][ancho-3]=2;
            this.casillas[23][ancho-4]=2;
            this.casillas[23][ancho-5]=2;
            this.casillas[23][ancho-6]=2;
            for(int i=18; i<22;++i){
                this.casillas[i][ancho-8]=2;
                this.casillas[i][ancho-7]=2;
            }
            for(int i=0; i<ancho;++i){
                this.casillas[i][0]=1;
                this.casillas[i][1]=1;
                this.casillas[i][ancho-2] = 1;
                this.casillas[i][ancho-1]=1;
            }
            for(int i=0; i<alto; ++i){
                this.casillas[0][i]=1;
                this.casillas[1][i]=1;
                this.casillas[ancho-2][i]=1;
                this.casillas[ancho-1][i]=1;
            }
            for(int i=6;i<ancho-6; i+=6){
                for(int j=6; j<16;++j ){
                    this.casillas[i][j]=2;
                    this.casillas[i+1][j]=2;
                    this.casillas[i+2][j]=2;
                }
                this.casillas[i][20]=2;
                this.casillas[i+1][20]=2;
                this.casillas[i+2][20]=2;
                this.casillas[i][19]=2;
                this.casillas[i+1][19]=2;
                this.casillas[i+2][19]=2;

                for(int j=24; j<ancho-6;++j ) {
                    this.casillas[i][j] = 2;
                    this.casillas[i + 1][j] = 2;
                    this.casillas[i + 2][j] = 2;
                }
            }
        }
        if(nivel==2){
            for(int i=0; i<ancho;++i){
                this.casillas[i][0]=1;
                this.casillas[i][1]=1;
                this.casillas[i][ancho-2] = 1;
                this.casillas[i][ancho-1]=1;
            }

            for(int i=10;i<ancho-10; i+=8){
                for(int j=6; j<16;++j ){
                    this.casillas[i][j]=2;
                    this.casillas[i+1][j]=2;
                    this.casillas[i+2][j]=2;
                }
                this.casillas[i][20]=1;
                this.casillas[i+1][20]=1;
                this.casillas[i+2][20]=1;
                this.casillas[i+3][20]=1;
                this.casillas[i][19]=1;
                this.casillas[i+1][19]=1;
                this.casillas[i+2][19]=1;
                this.casillas[i+3][19]=1;
                this.casillas[i-1][20]=1;
                this.casillas[i-1][19]=1;


                for(int j=24; j<ancho-10;++j ) {
                    this.casillas[i][j] = 2;
                    this.casillas[i + 1][j] = 2;
                    this.casillas[i + 2][j] = 2;
                }
            }
        }

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

