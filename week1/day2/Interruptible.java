import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Interruptible {
  public static void main (String[] args) {
    final ReentrantLock l1 = new ReentrantLock();
    final ReentrantLock l2 = new ReentrantLock();
    Object a = new Object();
    Object b = new Object();
    Thread t1 = new Thread() {
      public void run() {
        int c = 1000;
        while ((c--) > 0) {
          try {
            l1.lockInterruptibly();
            Thread.sleep(1000);
            if (l2.tryLock(1000, TimeUnit.MILLISECONDS)) {
              System.out.println("t1 print:" + b);
            } else {
              System.out.print("t1 give up lock");
            }
          } catch (InterruptedException e) {
          
          } finally {
            l2.unlock();
            l1.unlock();
          }
        }
      }
    };

    Thread t2 = new Thread() {
      public void run() {
        int c = 1000;
        while ((c--) > 0) {
          try {
            l2.lockInterruptibly();
            Thread.sleep(1000);
            if (l1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                Thread.sleep(3000);
            } else {
              System.out.println("t2 give up lock");
            }
          } catch (InterruptedException e) {
        
          } finally {
            l1.unlock();
            l2.unlock();
          }
        }
      }
    };
    t1.start();
    t2.start();
  }
}
