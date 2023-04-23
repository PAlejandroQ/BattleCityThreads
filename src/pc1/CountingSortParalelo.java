package pc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public  class CountingSortParalelo {
    public static void main(String[] args){
        int maxValue = 100000000;
        int[] datos = generateRandomArray(maxValue,0,maxValue);
        int numThreads = 8;
        System.out.println("nThreads    CountingSort    ParallelSort");
        for(int i=1; i<=100;i++) {
            numThreads = i;
            long startTime = System.currentTimeMillis();
            countingSort(datos, numThreads);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
//            System.out.println("Tiempo de ejecucion CountingSortParalelo: " + duration + " millisegundos.");

            startTime = System.currentTimeMillis();
            Arrays.parallelSort(datos);
            endTime = System.currentTimeMillis();
            long duration2 = endTime - startTime;
//            System.out.println("Tiempo de ejecucion parallelSort: " + duration2 + " millisegundos.");
//            System.out.println(numThreads + "\t" + duration + "\t" + duration2);
            System.out.println(numThreads + "\t" + duration + "\t" + duration2);
        }
//        System.out.println(Arrays.toString(datos));
    }
    public static void countingSort(int[] datos, int numThreads) {
        int max = Arrays.stream(datos).max().getAsInt();
        int[] counts = new int[max+1];
        List<Thread> threads = new ArrayList<>();
        int d = (int) (datos.length/numThreads);
        for(int i = 0; i < numThreads-1; i++){
            int threadNum = i;
            Thread t = new Thread(() -> {
                for(int j = threadNum*d; j < threadNum*d + d; j+= 1) {
                    counts[datos[j]]++;
                }
            });
            threads.add(t);
            t.start();
        }
        Thread tF = new Thread(() -> {
            for (int j = (d * (numThreads - 1)) ; j < datos.length; j += 1) {
                counts[datos[j]]++;
            }
        });
        threads.add(tF);
        tF.start();

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(Arrays.toString(datos));
//        System.out.println(Arrays.toString(counts));
        for(int i = 1; i < counts.length; i++){
            counts[i] += counts[i-1];
        }
        int[] sortedArr = new int[datos.length];
        for(int i = datos.length - 1; i >= 0; i--){
            sortedArr[counts[datos[i]] - 1] = datos[i];
            counts[datos[i]]--;
        }
        System.arraycopy(sortedArr, 0, datos, 0, datos.length);
    }
    public static int[] generateRandomArray(int size, int min, int max) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }
        return array;
    }

}