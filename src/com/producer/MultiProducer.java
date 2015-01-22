/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.producer;

import com.consumer.MultiConsumer;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author RaghuNandan
 */
public class MultiProducer implements Runnable {

    LinkedBlockingQueue<Integer> q; 
    ArrayList<Thread> prodList; 
    boolean isMainProducerThreadCreated = true;
    int prodCount=0;
    int MAXSIZE;    
    
    public MultiProducer(LinkedBlockingQueue<Integer> q, ArrayList<Thread> prodList,int prodCount,int MAXSIZE) {
     this.q = q;
     this.prodList = prodList;    
     this.prodCount = prodCount;
     this.MAXSIZE = MAXSIZE;
    }
    
    @Override
    public void run() {
        try {
         //Create First/Main Producer
           if(isMainProducerThreadCreated)
           {
               System.out.println(Thread.currentThread().getName()+ " thread has started ...");
               isMainProducerThreadCreated = false;
               while(true)
               addObject();               
               
           }
         //Create subsequent Producers
           else
           {
               System.out.println(Thread.currentThread().getName()+" thread has started ...");
               while(q.size()!=MAXSIZE)
               {
                   
               }
           }    
        } catch (Exception e) {
        }
    }

    private synchronized void addObject() throws Exception{
       
       double d= Math.random()*100;
       int i = (int) d;
                   
       q.put(new Integer(i));
       System.out.println(Thread.currentThread().getName()+" : added the object i: "+i);
        System.out.println(q);
                 
    }

    
    
}
