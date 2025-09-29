/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoasyncjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author drago
 */
public class Demo_SumNumbers {
        public static void main(String[] args) throws Exception {
        int[] arr_1 = new int[1_000_000];
         int[] arr_2 = new int[1_000_000];
         
            for (int i = 0; i < 1_000_000; i++) {
                arr_1[i] = i;
                arr_2[i] = i;
            }   
            
            long startTime = System.currentTimeMillis();
            
            long result_1 = sum(arr_1);
             long result_2 = sum(arr_2);
             long sum = result_1 + result_2;

//            ExecutorService executor = Executors.newFixedThreadPool(2);
//            Future<Long> result_1 = executor.submit( () ->sum(arr_1));
//            Future<Long> result_2 = executor.submit( () ->sum(arr_2));
//            long sum = result_1.get() + result_2.get();
//            executor.shutdown();
             
             long endTime = System.currentTimeMillis();
             System.out.println( (endTime - startTime) + " - " + sum);
    }
        
        private static long sum(int[] arr){
            long result =0;
            for (int i = 0; i < 1_000_000; i++)   result +=arr[i];
            return result;
        }
}
