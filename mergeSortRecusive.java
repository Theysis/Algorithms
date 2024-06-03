import java.io.BufferedReader;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
    /**
* Author: Joshua Wyatt
* mergesort through recursion.
*/ 
public class CollectData {
   public static void main(String[] args) {
      int L = 150000;
      ArrayList<Integer> g = new ArrayList<Integer>(L);
      Random generator = new Random();
      for (int stuffer = 0; stuffer < L; stuffer++) {
         int k = generator.nextInt(0x7ffffffe);
         g.add(stuffer, k);
      }
      ArrayList<Integer> A = new ArrayList<Integer>(L);
      int index = 0;
      //File open portion //
      long time; //Variable for starting time
      double tmergeN; //Variable for total merge time
      double tnaiveN; //Variable for total naive time
      int n = 1;
      try (PrintWriter writer = new PrintWriter("collectData.csv")) {
         StringBuilder sb = new StringBuilder();
		 sb.append("n,");
         sb.append("Time Complexity of MergeSort,");
         sb.append("Time Complexity of NaiveSort,\n");
         for (n = 4000; n <= L; n = n + 1000) {
            while (index < n) {
               A.add(g.get(index));
               index++;
            }
            // index = n;
            ArrayList<Integer> B = A;
            time = System.currentTimeMillis(); // Takes starting time
            mergeSort(A, 1, n);
            tmergeN = ((double)(System.currentTimeMillis() - time) ) / 1000; // Calculates total time
            time = System.currentTimeMillis();
            naiveSort(B);
            tnaiveN = ((double)(System.currentTimeMillis() - time) ) / 1000;
            // System.out.println(tn);
            sb.append(n);
            sb.append(",");
            sb.append(tmergeN);
            sb.append(",");
            sb.append(tnaiveN);
            sb.append(",\n");
            
         }
         writer.write(sb.toString());
      } catch (FileNotFoundException e) {
      } 
   }


   /** the primary recursion algorithm for mergeSort function */
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
   
   /** Puts sorted arrays together for mergeSort */
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
   
   /** Naive algorithm for process */
   public static void naiveSort(ArrayList<Integer> a) {
      int n = a.size();
      int buffer = 0;
      for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            if (a.get(j) < a.get(i)) {
               buffer = a.get(i);
               a.set(a.get(i),a.get(j));
               a.set(a.get(j),buffer);
            }
         }
      
      }
   
   }
   
   
  
   
}
