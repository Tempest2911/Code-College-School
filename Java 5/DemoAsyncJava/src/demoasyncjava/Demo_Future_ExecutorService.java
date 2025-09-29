/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoasyncjava;


import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 * @author drago
 */
public class Demo_Future_ExecutorService {
        public static void main(String[] args) throws Exception {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            
            // mất 2 giây để xử lý
            Callable<String> task = () -> {  //tạo hàm
                Thread.sleep(2000);  //Giả sử xử lý mất 2s
                return "Hehe";
            };
            
            //Kết quả tương lai
            Future<String> future = executor.submit(task);
            
            //Trong lúc làm vc khác
            System.out.println("Đang lm vc khác....");
            
            //Chờ kết quả thực hiện xong
            String result = future.get();
            System.out.println(result);
            
            executor.shutdown();
            
    }
}
