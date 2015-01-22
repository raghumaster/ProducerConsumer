//package com.main;
//import com.consumer.Consumer;
//import com.producer.Producer;
//import com.sun.org.apache.xml.internal.serialize.Serializer;
//import java.util.ArrayList;
//import java.util.Queue;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.Semaphore;
//import javax.management.monitor.Monitor;
///**
// *
// * @author RaghuNandan
// */
//public class ProblemSimulator {
//    LinkedBlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>(100); 
//    ArrayList<Producer> prodList = new ArrayList<Producer>();
//    ArrayList<Consumer> conList = new ArrayList<Consumer>();  
//    
//    int MAXSIZE=60;
//    public static void main(String[] args)
//    {
//    ProblemSimulator obj = new ProblemSimulator();
//    ArrayList<Integer> threadStatus = new ArrayList<Integer>();
//    
//    Semaphore semProd = new Semaphore(1);
//    Semaphore semCon = new Semaphore(0);
//    
//    Thread t2 = new Thread(new Consumer(obj.q,threadStatus,semCon,semProd),"Consumer1");
//     t2.start();  
//        
//     Thread t1 = new Thread(new Producer(obj.q,threadStatus,semProd,semCon),"Producer1");
//     t1.start();
//     
//     
//     
//    }
//    
//    
//    
//}
