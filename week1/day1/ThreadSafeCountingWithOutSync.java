public class ThreadSafeCountingWithOutSync {
  public static void main(String[] args) throws InterruptedException {
    class Counter {
      private int count = 0;
      
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
    ct1.start();
    ct2.start();
    Thread.yield();
    ct1.join();
    ct2.join();
    //The right answer should be 20,000
    System.out.println(counter.getCount());
  }
}
