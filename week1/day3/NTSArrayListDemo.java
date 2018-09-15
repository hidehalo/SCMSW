import java.util.ArrayList;
import java.util.Iterator;

class NTSArrayListDemo extends Thread {
    static ArrayList<String> arrayList = new ArrayList<String>();

    public void run() {
        for (int i = 0;i<1000;i++) {
            arrayList.add("CONCURRENT");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NTSArrayListDemo t1 = new NTSArrayListDemo();
        NTSArrayListDemo t2 = new NTSArrayListDemo();
        NTSArrayListDemo t3 = new NTSArrayListDemo();
        t1.start();
        arrayList.add("A");
        t2.start();
        arrayList.add("B");
        t3.start();
        arrayList.add("C");
        t1.join();
        t2.join();
        t3.join();
        System.out.println(arrayList.size()); 
    }
}