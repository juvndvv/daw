import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AlgorithmComparison {

    public static int[] generateVector(int length) {
        int[] v = new int[length];

        for (int i = 0; i < length; i++) {
            v[i] = (int) (Math.random() * length);
        }

        return v;
    }

    public static void main(String[] args) {
        int[] lengths = { 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

        FileWriter file = null;
        PrintWriter pw = null;

        try {
            file = new FileWriter("./out/data.csv");
            pw = new PrintWriter(file);

        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int length : lengths) {

            
            int v[];

            try {
                v = generateVector(length);
            } catch (OutOfMemoryError e) {
                pw.println("OutOfMemory,OutOfMemory,OutOfMemory");
                continue;
            }

            long start;
            long end;
            int[] vCopy;

            // quicksort
            try {
                vCopy = v.clone();
                start = System.currentTimeMillis();
                SortAlgorithms.quicksort(vCopy, 0, length - 1);
                end = System.currentTimeMillis();
                pw.printf("%d,", end - start);
            } catch (OutOfMemoryError e) {
                pw.printf("OutOfMemory,");
            } finally {
                System.out.printf("COMPLETE quicksort for %d positions%n", length);
            }

            // merge sort
            try {
                vCopy = v.clone();
                start = System.currentTimeMillis();
                SortAlgorithms.mergeSort(vCopy, length);
                end = System.currentTimeMillis();
                pw.printf("%d,", end - start);
            } catch (OutOfMemoryError e) {
                pw.printf("OutOfMemory,");
            } finally {
                System.out.printf("COMPLETE merge sort for %d positions%n", length);
            }

            // heapsort
            try {
                vCopy = v.clone();
                start = System.currentTimeMillis();
                SortAlgorithms.heapsort(vCopy);
                end = System.currentTimeMillis();
                pw.printf("%d", end - start);
            } catch (OutOfMemoryError e) {
                pw.printf("OutOfMemory");
            } finally {
                System.out.printf("COMPLETE heapsort for %d positions%n", length);
            }

            pw.println();
        }

        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
