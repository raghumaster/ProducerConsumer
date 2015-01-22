package com.main;

import com.consumer.MultiConsumer;
import com.producer.MultiProducer;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Raghu Nandan
 */
public class DynamicMain {
    
    LinkedBlockingQueue<Integer> q;
    ArrayList<Thread> conList = new ArrayList<Thread>(); 
    ArrayList<Thread> prodList = new ArrayList<Thread>(); 
    
    int prodCount=0;
    int conCount=0;
    boolean mainConsumer = true;
    int MAXSIZE=60;

    public DynamicMain() {
        q = new LinkedBlockingQueue<Integer>(MAXSIZE);
    }
    
    
    
    public static void main(String[] args) {
        try {
            DynamicMain obj = new DynamicMain();
            
            
            //Spawn the initial threads
           Thread prod = new Thread(new MultiProducer(obj.q,obj.prodList,obj.prodCount,obj.MAXSIZE),"Main Producer");
           prod.start();
           
           Thread con = new Thread(new MultiConsumer(obj.q,obj.conList,obj.conCount,obj.MAXSIZE,obj.mainConsumer),"Main Consumer");
           con.start();
           
           //Dynamically decide creation of subsequent threads
           
           
           
     while(true)
     {  
      //System.out.println("The value of conList , "+ obj.conList + "  q.size() and conlist.size() "+ obj.q.size()+" and "+ obj.conList.size());   
      obj.spawnNewConsumer();   
     }                
   } 
        catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    public void spawnNewConsumer()
    {
        
        
     if(this.q.size()>(this.MAXSIZE*3)/4 && conList.size()==2)
     {
       System.out.println("Queue is 3/4th full, spawning a new consumer");  
       Thread t= new Thread(new MultiConsumer(this.q,this.conList,prodCount,MAXSIZE,mainConsumer),"helper3"); 
       conList.add(t);
       t.start();
     }   
     if(this.q.size()>this.MAXSIZE/2 && conList.size()==1)
     {
       System.out.println("Queue is 1/2 full, spawning a new consumer");  
       Thread t= new Thread(new MultiConsumer(this.q,this.conList,prodCount,MAXSIZE,mainConsumer),"helper1"); 
       conList.add(t);
       t.start();
     }
        
     if(this.q.size()>this.MAXSIZE/4 && conList.size()==0)
     {
         System.out.println("Queue is 1/4th full, spawning a new consumer");  
       Thread t= new Thread(new MultiConsumer(this.q,this.conList,prodCount,MAXSIZE,mainConsumer),"helper1"); 
       conList.add(t);
       t.start();
     }    
    }
    
    
    public void spawnNewProducer()
    {
      if(this.q.size()>this.MAXSIZE/4)
      {
          Thread t = new Thread(new MultiProducer(this.q,this.prodList,prodCount,MAXSIZE),"");
          prodList.add(t);
          prodList.get(prodCount++).start();
      }
    }

    private void terminateExistingConsumer() {
    if(this.q.size()<this.MAXSIZE/2)
    {}
    }
    
}
