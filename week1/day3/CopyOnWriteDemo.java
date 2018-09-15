import java.util.concurrent.CopyOnWriteArrayList; 
import java.util.*;

class CopyOnWriteDemo extends Thread {
    static CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
    
    public void run() {
        for (int i = 0; i < 1000;i++) {
            copyOnWriteArrayList.add("CONCURRENT");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteDemo t1 = new CopyOnWriteDemo(); 
        t1.start(); 
        copyOnWriteArrayList.add("A"); 
        CopyOnWriteDemo t2 = new CopyOnWriteDemo(); 
        t2.start(); 
        copyOnWriteArrayList.add("B"); 
        CopyOnWriteDemo t3 = new CopyOnWriteDemo(); 
        t3.start(); 
        copyOnWriteArrayList.add("C");
        t1.join();
        t2.join();
        t3.join();
        System.out.println(copyOnWriteArrayList.size()); 
    }
}