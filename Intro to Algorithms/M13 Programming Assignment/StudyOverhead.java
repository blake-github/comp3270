import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.lang.Math;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;


public class StudyOverhead
{
   static int n = 2000;
   static int[] s = new int[n];
   static int[] f = new int[n];
   public static void main(String[] args) throws FileNotFoundException {
      int numberP = 2000;
      int numberR = 2000;
      initArrays(n);
      ArrayList<Long> M = study(numberP, numberR);
      System.out.println("M[i]");
   }
    
   public static ArrayList<Long> study(int NumberPoints, int NumberRuns) {
      ArrayList<Long> M = new ArrayList<Long>();
      List<Integer> out = new ArrayList<Integer>();
      long TimeRecursive, TimeIterative;
      long time, startTime, stopTime;
      try(PrintWriter writer = new PrintWriter("Output.csv")){
         StringBuilder sb = new StringBuilder();
         sb.append("i");
         sb.append(",");
         sb.append("M[i]");
         for (int i = 1; i <= NumberPoints; i++)
         {
            TimeRecursive = 0;
            TimeIterative = 0;
            for (int j = 1; j <= NumberRuns; j++)
            {
               out = new ArrayList<>();
               time = 0;
               startTime = System.nanoTime();
               RecursiveActivitySelector(s, f, 0, i - 1, out);
               stopTime = System.nanoTime();
               time = stopTime - startTime;
               TimeRecursive += time;
               time = 0;
               startTime = System.nanoTime();
               GreedyActivitySelector(s, f, i - 1);
               stopTime = System.nanoTime();
               time = stopTime - startTime;
               TimeIterative += time;
            }
            sb.append("\n");
            sb.append(String.valueOf(i));
            sb.append(",");
            sb.append(String.valueOf((float)TimeRecursive / TimeIterative));
            System.out.println(Integer.toString(i) + " iterations done");
         }
         writer.write(sb.toString());
         writer.close();
      }
      catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      
      }
      return M;
   }
   
   public static void initArrays(int n)
   {
      s[0] = 0;
      f[0] = 0;
      for (int i = 1; i < n; i++)
      {
         if ((i % 2) == 0)
         {
            s[i] = f[i - 2];
            f[i] = s[i] + 2;
         }
         else
         {
            s[i] = f[i-1] - 1;
            f[i] = f[i-1] + 1;
         }
      }
   }
   
    
   // public static int[] RecursiveActivitySelector(int k, int n, int[] out) {
      // int m = k + 1;
      // while (m <= n && s[m] <= f[k])
      // {
         // m = m + 1;
      // }
      // if (m <= n)
      // {
         // return union(RecursiveActivitySelector(m, n - 1, out), m);
      // }
      // return new int[0];
   // }
//    
   // public static int[] GreedyActivitySelector(int n, int[] out)
   // {
      // out = union(out, 1);
      // int k = 0;
      // for (int m = 1; m <= n; m++)
      // {
         // if (s[m] >= f[k])
         // {
            // out = union(out, m);
            // k = m;
         // }
      // }
      // return out;
   // }
   
   public static List<Integer> RecursiveActivitySelector(int[] s, int[] f, int k, int n, List<Integer> A) {
      int m = k + 1;
   
      while (m <= n && s[m] <= f[k]) {
         m = m + 1;
      }
   
      if (m <= n) {
         A.add(m);
         RecursiveActivitySelector(s, f, m, n, A);
         return A;
      }
      
      else {
         return null;
      }
   }

   public static List<Integer> GreedyActivitySelector(int[] s, int[] f, int n) {
      List<Integer> A = new ArrayList<Integer>();
      A.add(1);
      int k = 1;
   
      for (int m = 2; m < n; m++) {
         if (s[m] >= f[k]) {
            A.add(m);
            k = m;
         }
      }
      return A;
   }
   
   public static int[] union(int[] array, int var)
   {
      int[] tree = Arrays.copyOf(array, array.length + 1);
      tree[array.length] = var;
      return tree;
   }
    
            
}