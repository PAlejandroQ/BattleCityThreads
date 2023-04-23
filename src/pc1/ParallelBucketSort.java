package pc1;

//import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class ParallelBucketSort {
    //Solo para numeros del 0 al 1 con distribucion uniforme
    public double[] arr;
    int n, h;
    public long time;
    Vector<Double>[] buckets;

    ParallelBucketSort( double[] arr, int n,  int h) {
        this.n = n;
        this.h = h;
        this.arr = arr;
        buckets = new Vector[n];
    }


    public void start() {
        long startTime = System.currentTimeMillis();
        this.bucketSort();
        long intervalTime = System.currentTimeMillis() - startTime;
        time = intervalTime;
    }


    public void bucketSort() {

        if (n <= 0)
            return;
        int seg = (int) (n / h);
        int residuo = (n%h);
//        System.out.print("Residuo:"+residuo);
        //Creando mis hilos para distribuir los buckets
        Thread[] t = new Thread[h + residuo];
        //Creando mis hilos para ordenar en cada bucket
        Thread[] p = new Thread[h + residuo];


        //Creando mis buckets
        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Double>();
        }

        //Agregando los elemntos en los correspondientes buckets de forma paralela
        for (int i = 1; i <= h; i++) {
            t[i - 1] = new Thread(new distributeInBacketsThread(seg * (i - 1), i * seg - 1, i - 1));
            t[i - 1].start();
        }
        if(residuo !=0){
            t[h] = new Thread(new distributeInBacketsThread(seg * h, n-1, h));
            t[h].start();
        }

        //Esperando a que se distribuya todos lo elementos en los buckets
        try {
            for (int i = 1; i <= h; i++) {
                t[i].join();
            }
        } catch (Exception e) {
        }

        //Ordenando cada bucket de forma paralela
        for (int i = 1; i <= h; i++) {
            p[i - 1] = new Thread(new solveEachBacketThread(seg * (i - 1), i * seg - 1, i - 1));
            p[i - 1].start();
        }
        if(residuo !=0){
            p[h] = new Thread(new solveEachBacketThread(seg * h, n-1, h));
            p[h].start();
        }

        //Esperando que todos los hilos terminen ordenar su parte
        try {
            for (int i = 1; i <= h; i++) {
                p[i].join();
            }
        } catch (Exception e) {
        }
        //Concatenando todos los backets y sobrescribiendo nuestro array original
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }


    private class MyThread {
        int start, end, indexThread;

        public MyThread(int start, int end, int indexThread) {
            this.start = start;
            this.end = end;
            this.indexThread = indexThread;
        }
    }


    //Threads
    private class distributeInBacketsThread extends MyThread implements Runnable {
        public distributeInBacketsThread(int start, int end, int indexThread) {
            super(start, end, indexThread);
        }

        public void run() {
            //Agregando en el bucket
//            System.out.print("\nSegmento:"+start+" - "+end+"\n");
            for (int i = start; i <= end; i++) {
                double idx = arr[i] * n;
                buckets[(int) idx].add(arr[i]);
            }
        }
    }

    private class solveEachBacketThread extends MyThread implements Runnable {
        public solveEachBacketThread(int start, int end, int indexThread) {
            super(start, end, indexThread);
        }

        public void run() {
            //Cada segmento tiene una cierta cantida de bucktes, aqui lo ordenamos cada una
            for (int i = start; i <= end; i++) {
                InsertionSort(buckets[i], i);
            }
        }
    }


    //Funciones complemntarias

    //Para ordenar cada bucket individual
    private void InsertionSort(Vector<Double> bucket, int indexBucket) {
        int n = bucket.size();
        Vector auxBucket = (Vector) bucket.clone();
        for (int i = 1; i < n; ++i) {
            double key = (double) auxBucket.get(i);
            int j = i - 1;
            while (j >= 0 && (double) auxBucket.get(j) > key) {
                bucket.set(j + 1, (double) auxBucket.get(j));
                j = j - 1;
            }
            bucket.set(j + 1, key);
        }
      buckets[indexBucket] = bucket;
    }


}
