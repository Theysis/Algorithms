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
public class mergeSort {
   public static void main(String[] args) {
      int L = 1000000;
      ArrayList<Integer> g = new ArrayList<Integer>(L);
      Random generator = new Random();
      for (int stuffer = 0; stuffer < L; stuffer++) {
         int k = generator.nextInt(100);
         g.add(stuffer, k);
      }
      ArrayList<Integer> A = new ArrayList<Integer>(L);
      int index = 0;
      //File open portion //
      long time; //Variable for starting time
      double tn; //Variable for total time
      int n = 1;
      try (PrintWriter writer = new PrintWriter("merge-sort.csv")) {
         StringBuilder sb = new StringBuilder();
         sb.append("n,");
         sb.append("t(n),");
         sb.append("t(n)/n,");
         sb.append("t(n)/n*sqrt(n),");
         sb.append("t(n)/n*log(n),\n");
         for (n = 1; n <= L; n = n + 1000) {
            while (index < n) {
               A.add(g.get(index));
               index++;
            }
            // index = n;
            time = System.currentTimeMillis(); // Takes starting time
            mergeSort(A, 1, n);
            tn = ((double)(System.currentTimeMillis() - time) ) / 1000; // Calculates total time
            // System.out.println(tn);
            sb.append(n);
            sb.append(",");
            sb.append(tn);
            sb.append(",");
            sb.append(tn/n);
            sb.append(",");
            sb.append(tn/(n * Math.sqrt(n)));
            sb.append(",");
            sb.append(tn/(n * Math.log10(n)));
            sb.append("\n");
            
         }
         writer.write(sb.toString());
      } catch (FileNotFoundException e) {
      } 
   }



   public static void mergeSort(ArrayList<Integer> A, int p, int r) {
   // Variables
   
      ArrayList<Integer> array = A;
      int left = p;
      int right = r;
      if (left < right) {
         int q = (int) (Math.floor((left+right)/2));
         mergeSort(array, left, q);
         mergeSort(array, q + 1, r);
         merge(array, left, q, right);
      }
   
   
   
   }

   public static void merge(ArrayList<Integer> A, int p, int q, int r) {
   //Variables 
      ArrayList<Integer> array = A;
      int left = p;
      int mid = q;
      int right = r;
      
      int buffer = 0; // holds value at array[i] or array[j]
      int i = 1;
      int j = 1;
      int n1 = mid - left + 1;
      int n2 = right - mid;
      ArrayList<Integer> l1 = new ArrayList<Integer>(n1 + 1);
      ArrayList<Integer> r2 = new ArrayList<Integer>(n2 + 1);
      for (i = 0; i < n1; i++) {
         buffer = array.get(left + i - 1);
         l1.add(i, buffer);
      } 
      for (j = 0; j < n2; j++) {
         buffer = array.get(mid + j);
         r2.add(j, buffer);
      }
      //l1.add(0x0fff);
      // r2.add(0x0fff);
      i = 0;
      j = 0;
      int k = left - 1;
      while (i < n1 && j < n2) {
         if (l1.get(i) <= r2.get(j)) {
            array.set(k, l1.get(i));
            i++;
         }
         else {
            array.set(k, r2.get(j));
            j++;
         }
         k++;
      }
      while (i < n1) {
         array.set(k, l1.get(i));
         i++;
         k++;
      }
      while (j < n2) {
         array.set(k, r2.get(j));
         j++;
         k++;
      }
     
    
   
   
   
   }
}