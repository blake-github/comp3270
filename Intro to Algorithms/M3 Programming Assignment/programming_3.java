import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class programming_3
{
   public static void main(String[] args) throws FileNotFoundException {
      collectData();
   }
    
   public static void collectData() {
      int L = 2000000;
      int[] G = new Random().ints(L + 1, 0, 214748369).toArray();
      int[] A = new int[L + 1];
      int[] sorted = new int[L + 1];
      try(PrintWriter writer = new PrintWriter("Output.csv")){
         StringBuilder sb = new StringBuilder();
         sb.append("n");
         sb.append(',');
         sb.append("Raw");
         sb.append(',');
         sb.append("Function 1");
         sb.append(',');
         sb.append("Function 2");
         sb.append(',');
         sb.append("Function 3");
         sb.append('\n');
         for (int n = 1000; n <= L; n += 1000) {
            for (int i = 1; i <= n; i++) {
               A[i] = G[i];
            }
            long startTime = System.nanoTime();
            sorted = mergeSort(A, 0, n);
            long stopTime = System.nanoTime();
            long time = stopTime - startTime;
            sb.append(String.valueOf(n));
            sb.append(',');
            sb.append(String.valueOf(time));
            sb.append(',');
            sb.append(String.valueOf(time / n));
            sb.append(',');
            sb.append(String.valueOf(time / (n * (Math.log10(n) / Math.log10(2.0)))));
            sb.append(',');
            sb.append(String.valueOf(time / (n * Math.sqrt(n))));
            sb.append('\n');
            System.out.println(String.valueOf(n) +" iterations done!");
         }
         writer.write(sb.toString());
         writer.close();
      }
      catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      
      }
      //System.out.println(Arrays.toString(sorted));
   }
    
   public static int[] mergeSort(int[] A, int p, int r) {
      if (p < r) {
         int q = (int)Math.floor((p + r) / 2);
         mergeSort(A, p, q);
         mergeSort(A, q + 1, r);
         merge(A, p, q, r);
      }
      return A;
   }
    
   public static void merge(int[] A, int p, int q, int r) {
      int n1 = q - p + 1;
      int n2 = r - q;
      int i = 1;
      int j = 1;
      int[] L = new int[n1 + 1 + 1];
      int[] R = new int[n2 + 1 + 1];
      for (i = 1; i <= n1; i++) {
         L[i] = A[p + i - 1];
      }
      for (j = 1; j <= n2; j++) {
         R[j] = A[q + j];
      }
      L[n1 + 1] = 0x0fffffff;
      R[n2 + 1] = 0x0fffffff;
      i = 1;
      j = 1;
      for (int k = p; k <= r; k++) {
         if (L[i] <= R[j]) {
            A[k] = L[i];
            i = i + 1;
         }
         else {
            A[k] = R[j];
            j = j + 1;
         }
      }
   }
}