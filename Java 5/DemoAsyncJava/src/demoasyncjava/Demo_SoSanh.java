/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoasyncjava;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author drago
 */
public class Demo_SoSanh {

    public static void fetchData(String name) {
        System.out.println("Start downloading...." + name);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println("\tDownload" + name);
        }
    }

    public static void xuLydongBo() {
        fetchData("JAV101");
        fetchData("JAV102");
        fetchData("JAV103");
    }

    public static void xuLyBatDongBo() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> fetchData("JAV101");
        Runnable task2 = () -> fetchData("JAV102");
        Runnable task3 = () -> fetchData("JAV103");
//            List<Runnable> tasks = List.of(task1, task2, task3);

        Future<?> future1 = executor.submit(task1);
        Future<?> future2 = executor.submit(task2);
        Future<?> future3 = executor.submit(task3);
        
        System.out.println(future1.get());
          System.out.println(future2.get());
            System.out.println(future3.get());
    }
    
    public static void main(String[] args)  throws Exception{
        System.out.println("Đo thời gian chạy đồng bộ ==========");
        long startTime = System.currentTimeMillis();
        xuLydongBo();
        long endTime = System.currentTimeMillis();
        System.out.println("Đồng bộ = " + (endTime - startTime));
        
        System.out.println("Đo thời gian chạy BẤT ĐỒNG BỘ ======");
        startTime = System.currentTimeMillis();
        xuLyBatDongBo();
        endTime = System.currentTimeMillis();
        System.out.println("Bất Đồng Bộ = " + (endTime - startTime));
    }
}
