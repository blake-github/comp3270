import java.io.PrintWriter;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.lang.Object;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;


public class programming_11
{
   public static void main(String[] args) throws FileNotFoundException {
      Scanner scan = new Scanner(System.in);
      System.out.println("Please enter the name of the input text file in format \"input.txt\"");
      String filePath = scan.nextLine();
      collectData(filePath);
   }
    
   public static void collectData(String fileName) {
      try{
         Scanner input = new Scanner(new FileReader(fileName));
         StringBuilder read = new StringBuilder();
         while (input.hasNext())
         {
            read.append(input.next());
            read.append(" ");
         }
         input.close();
         String graphString = read.toString();
         String[] tokens = graphString.split(Pattern.quote(" "));
         Graph graph = new Graph();
         Node source = null;
         Node node = null;
         Edge edge = null;
         for (int i = 0; i < tokens.length; i++)
         {
            String string = tokens[i];
            boolean isEdge = false;
            if (string.contains(","))
            {
               isEdge = true;
            }
            if (isEdge == false)
            {
               node = new Node(Integer.parseInt(string), 0);
               if (i == 0)
               {
                  source = node;
                  continue;
               }
               graph = graph.addNode(graph, node);
            }
            else
            {
               // String[] edgeValues = string.split(Pattern.quote(","));
               // edge = new Edge(node, graph.getNode(graph, Integer.parseInt(edgeValues[0])), Integer.parseInt(edgeValues[1]));
               // graph = graph.addEdge(graph, edge);
            }
         }
         for (int i = 0; i < tokens.length; i++)
         {
            String string = tokens[i];
            boolean isEdge = false;
            if (string.contains(","))
            {
               isEdge = true;
            }
            if (isEdge == false)
            {
               node = new Node(Integer.parseInt(string), 0);
            }
            else
            {
               String[] edgeValues = string.split(Pattern.quote(","));
               edge = new Edge(node, graph.getNode(graph, Integer.parseInt(edgeValues[0])), Integer.parseInt(edgeValues[1]));
               graph = graph.addEdge(graph, edge);
            }
            if (i == 0)
            {
               //source = node;
            }
         }
         boolean cycle = BellmanFord(graph, source);
         if (cycle == false)
         {
            System.out.println("The graph has a negative cycle in it or something is wrong");
            return;
         }
         String post = graph.toString(graph, source);
         System.out.println(post);
         try(PrintWriter writer = new PrintWriter("outputShortestPaths.txt")){
            StringBuilder sb = new StringBuilder();
            sb.append(post);
            writer.write(sb.toString());
            writer.close();
         }
      }
      catch (FileNotFoundException e) {
         System.out.println(e.getMessage());
      
      }
      //System.out.println(Arrays.toString(sorted));
   }
   
    
   public static boolean BellmanFord(Graph G, Node s) {
      initialize(G, s);
      for (int i = 0; i < G.V - 1; i++)
      {
         for (Edge ed : Graph.edges)
         {
            relax(G, ed);
         }
      }
      for (Edge ge : Graph.edges)
      {
         if (G.getNode(G, ge.dest.key).value > G.getNode(G, ge.parent.key).value + ge.weight)
         {
            return false;
         }
      }
      return true;
   }
   
   public static void initialize(Graph G, Node s)
   {
      for (Node n : G.nodes)
      {
         n.value = Integer.MAX_VALUE;
      }
      G.getNode(G, s.key).value = 0;
   }
   
   public static void relax(Graph G, Edge e)
   {
      int checking = G.getNode(G, e.dest.key).value;
      int checking2 = G.getNode(G, e.parent.key).value;
      int checking3 = checking2 + e.weight;
      if (checking2 != Integer.MAX_VALUE)
      {
         if (G.getNode(G, e.dest.key).value > G.getNode(G, e.parent.key).value + e.weight)
         {
            G.getNode(G, e.dest.key).value = G.getNode(G, e.parent.key).value + e.weight;
            G.getNode(G, e.dest.key).parent = e.parent;
         }
      }
   }
    
            
   
   public static class Graph {
       // A class to represent a weighted edge in graph
      int V, E;
      static ArrayList<Edge> edges;
      ArrayList<Node> nodes;
      Graph()
      {
         this.V = this.E = 0;
         this.edges = new ArrayList<Edge>();
         this.nodes = new ArrayList<Node>();
      }
      
      public static Node getNode(Graph G, int x)
      {
         Node ret = null;
         for (Node n : G.nodes)
         {
            if (n.key == x)
            {
               ret = n;
            }
         }
         return ret;
      }
      
      public static Graph addNode(Graph g, Node n)
      {
         g.nodes.add(n);
         g.V = g.V + 1;
         return g;
      }
      
      public static Graph addEdge(Graph g, Edge e)
      {
         g.edges.add(e);
         g.E = g.E + 1;
         return g;
      }
      
      public static String toString(Graph g, Node source)
      {
         String post = "";
         ArrayList<String> chaser = new ArrayList<String>();
         for (Node n : g.nodes)
         {
            if (n.key != source.key)
            {
               chaser = new ArrayList<String>();
               String end = Integer.toString(n.key);
               post = post + Integer.toString(n.key) + ": ";
               while (n.parent != null)
               {
                  n = g.getNode(g, n.parent.key);
                  chaser.add(Integer.toString(n.key));
               }
               for (int i = chaser.size() - 1; i > 0; i--)
               {
                  post = post + chaser.get(i - 1) + " ";
               }
               post = post + end + "\n";
            }
         }
         return post;
      }
   }
   
   public static class Edge {
      Node parent, dest;
      int weight;
      Edge()
      {
         this.parent = null;
         this.dest = null;
         this.weight = 0;
      }
      Edge(Node pr, Node destination, int w)
      {
         this.parent = pr;
         this.dest = destination;
         this.weight = w;
      }
   };

   
   public static class Node{  
      int key, value; 
      Node parent;
     
      public Node(int key, int data){  
            //Assign data to the new node, set left and right children to null  
         this.key = key;
         this.value = data; 
         this.parent = null;
      }  
   }  
}