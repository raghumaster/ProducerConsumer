/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.consumer;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author RaghuNandan
 */
public class MultiConsumer implements Runnable {
   
    LinkedBlockingQueue<Integer> q;
    ArrayList<Thread> conList; 
    boolean mainConsumer;
    int conCount;
    int MAXSIZE;

    public MultiConsumer(LinkedBlockingQueue<Integer> q, ArrayList<Thread> conList,int conCount,int MAXSIZE,boolean mainConsumer) {
      this.q = q;
      this.conList = conList;
      this.conCount = conCount;
      this.MAXSIZE = MAXSIZE;
      this.mainConsumer = mainConsumer;
    }
        
    @Override
    public void run() {
        try {
         //Create First/Main Consumer
            System.out.println("The name here is ..."+Thread.currentThread().getName());
         if(Thread.currentThread().getName().equals("Main Consumer"))
         {
             System.out.println(Thread.currentThread().getName()+" thread has started....");
             mainConsumer=false;
             while(true)
             {
                 Thread.sleep(10);
                 removeObj();
             }    
         }   
         //Create subsequent Consumers
         else
         {
             System.out.println(Thread.currentThread().getName()+ "dynamic thread started ...");
            boolean state = true;
             while(state)
            {
                if(q.size()==0)
                {
                    //System.out.println("conList "+conList);
                    try {
                        conList.remove(Thread.currentThread());
                        state = false;
                        System.out.println("Queue became empty hence forth removing the thread");
                        continue;
                        
                        //Thread.currentThread().interrupt();
                    } catch (Exception e) {
                        System.out.println("Exception while stopping thread");
                        e.printStackTrace();
                        
                    }
                }
                removeObj();
            }
         }      
        } catch (Exception e) {
        }
    }
    
    
    public synchronized void removeObj() throws Exception
    {
        Integer i = q.take();
        System.out.println(Thread.currentThread().getName()+ " : Removed object :"+i);
    }
}
