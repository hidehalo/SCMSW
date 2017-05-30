public class ThreadSafeCountingWithOutSync {
  public static void main(String[] args) throws InterruptedException {
    class Counter {
      private volatile int count = 0;
      
      public int getCount() {
        return count;
      }

      public void CAS(int init, int step) {
        if (init != this.count) {
          this.count += step;
        } else {
          this.count = init + step;
        }
      }
    };

    final Counter counter = new Counter();

    class CountingThread extends Thread {
      public void run() {
        int init, copy;
        init = counter.getCount();
        copy = init;
        for (int i = 0; i < 10000 ; ++i) {
          ++copy;
        }
        counter.CAS(init, copy);
      }
    };

    CountingThread ct1 = new CountingThread();
    CountingThread ct2 = new CountingThread();
    CountingThread ct3 = new CountingThread();
    CountingThread ct4 = new CountingThread();
    CountingThread ct5 = new CountingThread();
    CountingThread ct6 = new CountingThread();
    CountingThread ct7 = new CountingThread();
    CountingThread ct8 = new CountingThread();
    CountingThread ct9 = new CountingThread();
    CountingThread ct10 = new CountingThread();
    ct1.start();
    ct2.start();
    ct3.start();
    ct4.start();
    ct5.start();
    ct6.start();
    ct7.start();
    ct8.start();
    ct9.start();
    ct10.start();
    Thread.yield();
    ct1.join();
    ct2.join();
    ct3.join();
    ct4.join();
    ct5.join();
    ct6.join();
    ct7.join();
    ct8.join();
    ct9.join();
    ct10.join();
    //The right answer should be 20,000
    System.out.println(counter.getCount());
  }
}
