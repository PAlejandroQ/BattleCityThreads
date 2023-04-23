package ejemplo01;

public class multimathiSol01 {

    int N = 2000;
    public int A[][], B[][], C[][];

    public static void main(String args[]) throws InterruptedException {
        new multimathiSol01().inicio();
    }

    void inicio() throws InterruptedException {
        int H = 12;
        int d = (int) (N / H);
        A = new int[N][N];
        B = new int[N][N];
        C = new int[N][N];
        Thread hilos[] = new Thread[40];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = (int) (Math.random() * 10) + 1;
                B[i][j] = (int) (Math.random() * 10) + 1;
            }
        }
        System.out.println("----------------");
//        for (int i = 0; i < A.length; i++) {
//            for (int j = 0; j < A.length; j++) {
//                System.out.print(A[i][j] + " ");
//            }
//            System.out.println("");
//        }
//        System.out.println("----------------");
//        for (int i = 0; i < B.length; i++) {
//            for (int j = 0; j < B.length; j++) {
//                System.out.print(B[i][j] + " ");
//            }
//            System.out.println("");
//        }
        System.out.println("****************");
        for (int i = 0; i < (H - 1); i++) {
            hilos[i] = new sumintermat(i * d + 1, i * d + d);
            hilos[i].start();
            //hilos[i].join();
        }
        hilos[H - 1] = new sumintermat(((d * (H - 1)) + 1), N);
        hilos[H - 1].start();
        //hilos[H-1].join();
        for (int i = 0; i < H; i++) {
            hilos[i].join();
        }

//        for (int i = 0; i < C.length; i++) {
//            for (int j = 0; j < C.length; j++) {
//                System.out.print(C[i][j] + " ");
//            }
//            System.out.println("");
//        }
    }

    public class sumintermat extends Thread {

        public int min, max;

        sumintermat(int min_, int max_) {
            min = min_ - 1;
            max = max_ - 1;
            System.out.println("min: " + min + " max: " + max);
        }

        public void run() {
            for (int k = min; k <= max; k++) {
                for (int j = 0; j < N; j++) {
                    for (int i = 0; i < N; i++) {
                        C[k][j] += A[k][i] * B[i][j];
                    }
                }
            }
        }
    }
}  //ynunezm@gmail.com
