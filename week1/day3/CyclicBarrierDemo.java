import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CyclicBarrierDemo {
    static class Task extends Thread {
        protected CyclicBarrier barrier;
        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("运行子任务");
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(8);
        int poolSize = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        executor.execute(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("等待子任务...");
                    barrier.await();
                    System.out.println("子任务全部执行完毕");
                    barrier.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i=0;i<7;i++) {
            executor.execute(new Task(barrier));
        }
        executor.shutdown();
    }
}