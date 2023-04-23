package pc1;


import java.util.Arrays;


public class MainSort {
    public static void main(String args[]) {
        //Generando numeros aleatorios entre 0 y 1 de manera uniforme
        int n = 10000000;
        long[] timesMyAlgorithm = new long[100];
        long[] timesParallerSort = new long[100];
        double[] auxArr = new double[10000000];

        for (int i = 0; i < n; i++) {
            auxArr[i] = (double) Math.random();
        }
//        Ejecutando mi algoritmo
        for (int h = 1; h <= 100; h++) {
            ParallelBucketSort currentSort = new ParallelBucketSort(auxArr.clone(), n, h);
            currentSort.start();//Ejecutando
            timesMyAlgorithm[h - 1] = currentSort.time;//Guardando el tiempo
            System.out.print("---con---------Numero de Hilos: " + h + "------------");
        }
        printArray(timesMyAlgorithm, "Tiempos para hilos de 1 al 100 My Algotimo");


//        Ejecutando el algoritmo parallelSort
        for (int h = 1; h <= 100; h++) {
            long startTime = System.currentTimeMillis();
            Arrays.parallelSort(auxArr.clone());
            timesParallerSort[h-1] = System.currentTimeMillis() - startTime;
        }
        printArray(timesParallerSort, "Tiempos para hilos de 1 al 100 Parallel sort");



//        Si quiere jecutar para un h y n determinados
//        ParallelBucketSort currentSort = new ParallelBucketSort(auxArr.clone(),n, 19);
//        printArray(currentSort.arr,"Initial Array");
//        currentSort.start();
//        printArray(currentSort.arr,"Sorted");
//        System.out.print("\nTiempo:"+currentSort.time+"\n");

    }

    //Para imprimir un array
    static void printArray(double[] arr, String message) {
        System.out.print("\n" + message + "\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + '\n');
        }
    }

    static void printArray(long[] arr, String message) {
        System.out.print("\n" + message + "\n");
        for (double el : arr) {
            System.out.print(el + " ");
        }
    }

}
