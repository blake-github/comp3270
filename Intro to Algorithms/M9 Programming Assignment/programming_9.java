import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class programming_9
{
   public static void main(String[] args) throws FileNotFoundException {
      collectData();
   }
    
   public static void collectData() {
      int L = 20000;
      Random rand = new Random();
      BinaryTree T = new BinaryTree();
      try(PrintWriter writer = new PrintWriter("Output.csv")){
         StringBuilder sb = new StringBuilder();
         sb.append("n");
         sb.append(',');
         sb.append("Height");
         sb.append('\n');
         for (int n = 250; n <= L; n += 250) {
            int sum_height = 0;
            for (int j = 1; j <= 5; j++)
            {
               for (int i = 1; i <= n; i++) {
                  int p = rand.nextInt(40000);
                  Node z = new Node(p);
                  treeInsert(T, z);
               }
               int heightInc = getHeight(T.root);
               T = new BinaryTree();
               sum_height = sum_height + heightInc;
            }
            int height = sum_height/5;
            sb.append(String.valueOf(n));
            sb.append(',');
            sb.append(String.valueOf(height));
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
   
    
   public static int getHeight(Node node) {
      if(node == null) {
         return 0;
      }
   
      int leftHeight = getHeight(node.left);
      int rightHeight = getHeight(node.right);
   
      return Math.max(leftHeight, rightHeight) + 1;
   }
    
   public static void treeInsert(BinaryTree T, Node z) {
      if(T.root == null) {
         T.root = z;
         return;
      }
      Node y = null;
      Node x = T.root;
      while (x != null)
      {
         y = x;
         if (z.key < x.key)
         {
            x = x.left;
         }
         else
         {
            x = x.right;
         }
      }
      z.parent = y;
      if (y == null)
      {
         T.root = z;
      }
      else if (z.key <= y.key)
      {
         y.left = z;
      }
      else
      {
         y.right = z;
      }
   }
            
   
   public static class BinaryTree {
   
   // attributes
      Node root = null;
   
   // constructors
      BinaryTree(Node root) {
         this.root = root;
      }
   
      BinaryTree() {
      
      }
   }

   
   public static class Node{  
      int key;  
      Node left;  
      Node right;  
      Node parent;
      
      public Node(int data){  
            //Assign data to the new node, set left and right children to null  
         this.key = data;  
         this.left = null;  
         this.right = null;  
         this.parent = null;
      }  
   }  
}