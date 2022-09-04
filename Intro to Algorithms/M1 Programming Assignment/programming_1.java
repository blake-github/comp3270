import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Deque;
import java.util.Scanner;
import java.lang.Object;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.lang.Math;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 *
 * Blake Moore and whatever notation the wantf=
 *
 */
public class programming_1 {

   // public programming_1()
   // {
   //      
   // }
   
   public static void main(String[] args) throws FileNotFoundException
   {
      collectData();
   }

  /**
    * 
    */
   public static void collectData()
   {
      int L = 20000;
      double value = 0;
      try(PrintWriter writer = new PrintWriter("Retry.csv")){
      
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
         for (int n = 100; n <= L; n += 100)
         {
            long startTime = System.nanoTime();
            value = computeSumPowers(0.25,n);
            long stopTime = System.nanoTime();
            long time = stopTime - startTime;
         //Print n and stopwatch time. 
         
            sb.append(String.valueOf(n));
            sb.append(',');
            sb.append(String.valueOf(time));
            sb.append(',');
            sb.append(String.valueOf((time/Math.sqrt(n))));
            sb.append(',');
            sb.append(String.valueOf((time/Math.pow(n, 2))));
            sb.append(',');
            sb.append(String.valueOf((time/(n*Math.log(n)))));
            sb.append('\n');
         
            System.out.println(String.valueOf(n) +" iterations done!");
         }
         writer.write(sb.toString());
      } catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      
      }
   }
   
   public static double computeSumPowers(double x, int n)
   {
      double sum = 0;
      for (int i = 1; i<= n; i++)
      {
         double prod = 1;
         for (int j = 1; j<= i; j++)
         {
            prod = prod * x; 
         }
         sum = sum + prod;
      }
      return sum;
   
   }
   
   
}
   
   
