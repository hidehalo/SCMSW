import java.util.concurrent.CountDownLatch;

class PrevTask extends Thread {
    protected CountDownLatch latch;

    public PrevTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("等待...");
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("运行");
    }
}

class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        PrevTask t1 = new PrevTask(latch);
        t1.start();
        PrevTask t2 = new PrevTask(latch);
        t2.start();
        PrevTask t3 = new PrevTask(latch);
        t3.start();
        for (int i=0;i<3;i++) {
            latch.countDown();
        }
    }
}