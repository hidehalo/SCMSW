public class ThreadSafeCountingWithOutSync {
  public static void main(String[] args) throws InterruptedException {
    class Counter {
      private int count = 0;
      public void increment() {
        ++count;
      }  
      public int getCount() {
        return count;
      }

      public void setCount(int count) {
        this.count = count;
      }
      public Object getCopy() throws CloneNotSupportedException {
        return this.clone();
      }
    };

    final Counter counter = new Counter();

    class CountingThread extends Thread {
      public void run() {
        try {
          Counter tmpCounter = (Counter) counter.getCopy();

          for (int i = 0; i < 10000 ; ++i) {
            tmpCounter.increment();
          }

          int ret = counter.getCount() + tmpCounter.getCount();
          System.out.println(tmpCounter.getCount());
          counter.setCount(ret);
        } catch (CloneNotSupportedException e) {
          e.printStackTrace();
        }
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
