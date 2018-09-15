import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

class ConcurrentHashMapDemo extends Thread {
    static ConcurrentHashMap<String, Integer> chp = new ConcurrentHashMap<String, Integer>();

    public void run() {
            int v = (new Random()).nextInt();
            System.out.println("Put: " + v);
            chp.put("Hot", v);
            System.out.println("Get: "+chp.get("Hot"));
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread>threads = new ArrayList<Thread>();
        for (int i = 0; i< 100;i++) {
            Thread thread = (Thread)new ConcurrentHashMapDemo();
            threads.add(thread);
            thread.start();
        }
        for (int i=0;i<threads.size();i++) {
            threads.get(i).join();
        }
    }
}