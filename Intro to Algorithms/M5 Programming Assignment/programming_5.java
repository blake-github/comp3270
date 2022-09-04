import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class programming_5
{
   public static void main(String[] args) throws FileNotFoundException {
      collectData();
   }
    
   public static void collectData() {
      int L = 150000;
      int[] G = new Random().ints(L + 1, 0, 214748369).toArray();
      int[] sorted = new int[L + 1];
      int[] sorted2 = new int[L + 1];
      try(PrintWriter writer = new PrintWriter("Data5.csv")){
         StringBuilder sb = new StringBuilder();
         sb.append("n");
         sb.append(',');
         sb.append("Naive");
         sb.append(',');
         sb.append("Merge");
         sb.append('\n');
         for (int n = 4000; n <= L; n += 1000) {
            int [] A = new int[n + 1];
            for (int i = 1; i <= n; i++) {
               A[i] = G[i];
            }
            long startTime = System.nanoTime();
            sorted = mergeSort(A, 0, n);
            long stopTime = System.nanoTime();
            long time = stopTime - startTime;
            long startTime2 = System.nanoTime();
            sorted2 = naiveSort(A);
            long stopTime2 = System.nanoTime();
            long time2 = stopTime2 - startTime2;
            sb.append(String.valueOf(n));
            sb.append(',');
            sb.append(String.valueOf(time2));
            sb.append(',');
            sb.append(String.valueOf(time));
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
      int i, j, k;  
      int n1 = q - p + 1;    
      int n2 = r - q;    
      
      int[] L1 = new int[n1];
      int[] R1 = new int[n2]; //temporary arrays  
      
    /* copy data to temp arrays */  
      for (i = 0; i < n1; i++)   
      { 
         L1[i] = A[p + i];   
      } 
      for (j = 0; j < n2; j++)   
      { 
         R1[j] = A[q + 1 + j];  
      }  
      
      i = 0; /* initial index of first sub-array */  
      j = 0; /* initial index of second sub-array */   
      k = p;  /* initial index of merged sub-array */  
      
      while (i < n1 && j < n2)    
      {    
         if(L1[i] <= R1[j])    
         {    
            A[k] = L1[i];    
            i++;    
         }    
         else    
         {    
            A[k] = R1[j];    
            j++;    
         }    
         k++;    
      }    
      while (i < n1)    
      {    
         A[k] = L1[i];    
         i++;    
         k++;    
      }    
      
      while (j < n2)    
      {    
         A[k] = R1[j];    
         j++;    
         k++;    
      }    
   }
   
   public static int[] naiveSort(int[] A)
   {
      int buffer = 0;
      for (int j = 1; j <= A.length - 2; j++)
      {
         for (int i = (j + 1); i <= A.length - 1; i++)
         {
            if (A[i] < A[j])
            {
               buffer = A[j];
               A[j] = A[i];
               A[i] = buffer;
            }
         }
      }
      return A;
   }
}