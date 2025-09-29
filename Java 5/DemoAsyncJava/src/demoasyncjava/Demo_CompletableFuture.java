/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demoasyncjava;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author drago
 */
public class Demo_CompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync( () -> {
                try {
                    Thread.sleep(10000);
            } catch (Exception e) {}
                  return "Hehe";
        });
        
        future.thenApply(data ->  "Processing " + data)
                .thenAccept( result -> System.out.println("result"));

                System.out.println("Doing something else...");
                Thread.sleep(5000);
                System.out.println("Hihi");
        }
}
