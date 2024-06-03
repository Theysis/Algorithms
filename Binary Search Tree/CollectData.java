import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
    /**
* Author: Joshua Wyatt
*
*/ 
public class CollectData {
  

   
   public static void main(String[] args) {
      int L = 20000;
      int sum_height = 0;
      int p = 0;
      int heightAtN = 0;
      Random generator = new Random();
      
      //File open portion //
      try (PrintWriter writer = new PrintWriter("collectData.csv")) {
         StringBuilder sb = new StringBuilder();
         sb.append("The Value of n,");
         sb.append("The height of n,\n");
         for (int n = 250; n <= L; n = n + 250) {
            sum_height = 0;
            for (int j = 0; j < 5; j++) {
               BST tree = new BST();
               for (int i = 0; i < n; i++) {
                  p = generator.nextInt(40000);
                  tree.insertTree(tree, tree.newNode(p));
               }
               
               sum_height += tree.findHeight(tree.root);
               tree.deleteTree(tree.root);
            }
            heightAtN = sum_height / 5;
            sb.append(n);
            sb.append(",");
            sb.append(heightAtN);
            sb.append(",\n");
         }
         writer.write(sb.toString());
      } catch (FileNotFoundException e) {
      } 
   }
  

   
}
class BST {
   private class Node {
      int key;
      Node left, right, parent;
   
      public Node(int k) { 
         key = k;
         Node left = null;
         Node right = null;
         Node parent = null;
      }
   }
   Node root;
   int height;
   
   BST() {
      root = null;
      height = 0;
   }
   
   public void insertTree(BST ro, Node z) {
      Node y = null;
      Node x = ro.root;
      while (x != null) {
         y = x;
         if (z.key < x.key) {
            x = x.left;
         }
         else {
            x = x.right;
         }
      }
      z.parent = y;
      if (y == null) {
         ro.root = z;
      }
      else if (z.key < y.key) {
         y.left = z;
      }
      else {
         y.right = z;
      }
      
      
   }
   
   public Node newNode(int k) {
      Node n = new Node(k);
      return n;
   }
   
   
   public int findHeight(Node n) {
      if (n == null) {
         return 0;
      }
      int leftHeight = findHeight(n.left);
      int rightHeight = findHeight(n.right);
      
      if (leftHeight > rightHeight) {
         return leftHeight + 1;
      }
      else {
         return rightHeight + 1;
      }
      
   }
   
   public void deleteTree(Node n) {
      n = null;
   }
}
