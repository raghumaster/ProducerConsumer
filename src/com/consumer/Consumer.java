/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.consumer;
import com.producer.Producer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author RaghuNandan
 */
public class Consumer implements Runnable{

    public Consumer(LinkedBlockingQueue<Integer> q,ArrayList<Integer> threadStatus,Semaphore semCon,Semaphore semProd) {
        this.q =q;
        this.threadStatus = threadStatus;
        this.semCon = semCon;
        this.semProd = semProd;
      }
  
    LinkedBlockingQueue<Integer> q;
    ArrayList<Integer> threadStatus;
    Semaphore semCon;
    Semaphore semProd;

    public Consumer(LinkedBlockingQueue<Integer> q) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void run() 
    {    
         this.removeObject();
//         this.semaphoreRemove();
        }
    
    public synchronized void removeObject()
    {
        try
        {
           System.out.println(Thread.currentThread().getName()+" thread has started....");
           System.out.println("Consumer  Q: "+q.hashCode());
           while(true){
           
           
//           while(q.isEmpty())
//           {
//               //System.out.println("Consumer : Queue is empty hence waiting ");
//               this.notify();
//           }
           
               Integer i = q.take();
               System.out.println(Thread.currentThread().getName()+ " : Removed object :"+i);
           
           }
        }
        catch(Exception e)
        {
            System.out.println("Consumer catch block");
            e.printStackTrace();
        }
    }
    
    public void semaphoreRemove() 
     {
         try {
             
             while(true)
            {               
                  semCon.acquire();
                       System.out.println(Thread.currentThread().getName()+" : removed the object i: "+ q.remove());
                  semProd.release();
             }
             
         } catch (Exception e) {
         }
         
     }
    
}
