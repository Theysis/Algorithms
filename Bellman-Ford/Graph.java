import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
* Author: Joshua Wyatt
* Implements Bellman-Ford Algorithm in Java. 
*/ 
public class Graph {
   private class Edge {
      int source; // Previous node
      int weight; // Estimated weight
      int destination;
      
      public Edge(int s, int w, int d) {
         source = s;
         weight = w;
         destination = d;
      }
   }
   
   private class Node {
      int vertex;
      int weight;
      Node path;
      
      // ArrayList<Node> path = new ArrayList<Node>();
      public Node(int k) {
         vertex = k;
      }
   }
   
   // Global Variables
   ArrayList<Edge> edge = new ArrayList<Edge>();
   int numV = 0;
   int numE = 0;
   int s = 0; // source
   
   
   Graph() {
     
   }
  
   // The Bellman-Ford Algorithm
   Node[] bellmanFordAlg(Graph g, int s) {
      Node dist[] = new Node[numV];
     // initalize single source
      for (int i = 0; i < numV; i++) {
         dist[i] = new Node(i);
         dist[i].weight = Integer.MAX_VALUE;
      }
      dist[s].weight = 0;
     
      for (int j = 1; j < numV; j++) {
         for (int k = 0; k < numE; k++) {
            int u = g.edge.get(k).source;
            int v = g.edge.get(k).destination;
            int wei = g.edge.get(k).weight;
            // RELAXES EDGES
            if (dist[v].weight > dist[u].weight + wei && dist[u].weight != Integer.MAX_VALUE) {
               dist[v].weight = dist[u].weight + wei;
               dist[v].path = (dist[u]);
               //dist[v].path.add(dist[u]);
            }
         }
      }
      
      // Checks for negative cycles
      for (int j = 0; j < numE; j++) {
         int u = g.edge.get(j).source;
         int v = g.edge.get(j).destination;
         int wei = g.edge.get(j).weight;
         if (dist[v].weight > dist[u].weight + wei && dist[v].weight != Integer.MAX_VALUE) {
            return null;
         }
      
      }
     
     
      return dist;
   }
  
  
   
       
   void makeEdge(int s, int dest, int weight) {
      this.numE++;
      this.edge.add(new Edge(s, weight, dest));
      
   }  
   
   
   public static void main(String[] args) throws FileNotFoundException {   
     // variables
      Graph bf = new Graph();
   
      try (PrintWriter writer = new PrintWriter("outputShortestPath.txt")) {
         StringBuilder sb = new StringBuilder();
      
      // Reads in the input file
         //bf.graphReader("graph.txt");
         int source;
         int destination;
         int weighs; 
      
         File text = new File("inputGraph.txt");
         Scanner input = new Scanner(text);
         bf.s = Integer.parseInt(input.nextLine());
         while (input.hasNext()) {
            source = input.nextInt();
            
            Scanner sc = new Scanner(input.nextLine()).useDelimiter(" |,");
            while (sc.hasNext()) {
               destination = Integer.parseInt(sc.next());
               weighs = Integer.parseInt(sc.next());
               bf.makeEdge(source, destination, weighs);
                  //bf.edge[bf.numE] = new Edge(source, destination, weighs);
                  //bf.numE++;
            }
            bf.numV++;
         }
         input.close();
         
         
         
         
         
         Node[] paths = bf.bellmanFordAlg(bf, bf.s);
         if (paths == null) {
            sb.append("Graph has a negative cycle");
         }
         else {
            for (int i = 0; i < bf.numV; i++) {
               sb.append(paths[i].vertex);
               sb.append(": ");
               sb.append(bf.s);
               sb.append(" ");
               if (paths[i].path != null && paths[i].path.vertex != bf.s) {
               //for (int k = 0; k < paths[i].path.size(); k++) {
                  sb.append(paths[i].path.vertex);
                  sb.append(" ");
               //}
               }
               if (paths[i].vertex != bf.s) {
                  sb.append(paths[i].vertex);
               }
               sb.append("\n");
            }
         }
         writer.write(sb.toString());
      } catch (FileNotFoundException e) {
      } 
   
   }


}
