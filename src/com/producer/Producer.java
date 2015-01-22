package com.producer;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable{

    public Producer(LinkedBlockingQueue<Integer> q,ArrayList<Integer> threadStatus,Semaphore semProd,Semaphore semCon) {
        this.q =q;
        this.threadStatus=threadStatus;
        this.semProd = semProd;
        this.semCon = semCon;
           }

    LinkedBlockingQueue<Integer> q;
    ArrayList<Integer> threadStatus;
    Semaphore semProd;
    Semaphore semCon;
    
    @Override
    public void run() {
         //Implementing the concept of wait and notify.
         this.addObject();
        
         //Implementing the concept of semaphores
         //this.semaphoreAdd();
    }
  
     public Queue<Integer> getQueue()
     {
         return this.q;
     }
     
     public synchronized void addObject()
     {
         try
        {
            System.out.println(Thread.currentThread().getName()+ " thread has started ...");
            System.out.println("Producer  Q: "+q.hashCode());
            
            while(true)
            {
//                if(q.size()==10) continue;
//                System.out.println(q);
//                if(q.size()<10)
//                {
                    double d= Math.random()*100;
                    int i = (int) d;
                   
                    q.put(new Integer(i));
                     System.out.println(Thread.currentThread().getName()+" : added the object i: "+i);
                
                
//                else
//                {
//                    
//                    System.out.println("Queue is full and wiating for consumer");
//                    this.wait();
//                            // should wait for any consumer to consume an item upon which producer is ready to produce again
//                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Producer Catch block");
            e.printStackTrace();
        }
     }
     
     public void semaphoreAdd() 
     {
         try {
             
             while(true)
            {
                System.out.println(q);
                
                    double d= Math.random()*100;
                    int i = (int) d;
                   
                    semProd.acquire();
                    q.add(new Integer(i));
                     System.out.println(Thread.currentThread().getName()+" : added the object i: "+i);
                     semCon.release();
                     
             }
             
         } catch (Exception e) {
         }
         
     }
}
