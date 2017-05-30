import java.util.ArrayList;
import java.util.List;

/**
 * <describe>
 * </describe>
 * Created by edwin on 2017/5/30.
 */

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();
        final Thread mainThread = Thread.currentThread();

        int threadCount = 8;
        final List<Thread> list = new ArrayList<>(8);
        for (int i = 0; i < threadCount; i++) {
            final int currentIndex = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    int init, copy;
                    init = counter.getCount();
                    copy = init;
                    for (int i = 0; i < 10000; ++i) {
                        ++copy;
                    }
                    try {
                        if (currentIndex != 0) {
                            list.get(currentIndex - 1).join();
                        } else {
                            mainThread.join();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter.CAS(init, copy);
                    System.out.println(counter.getCount());
                }
            };
            list.add(thread);
            thread.start();
        }
        //The right answer should be 20,000
        System.out.println(counter.getCount());
    }

    private static class Counter {
        private int count = 0;

        private int getCount() {
            return count;
        }

        private void CAS(int init, int step) {
            if (init != this.count) {
                this.count += step;
            } else {
                this.count = init + step;
            }
        }
    }

}
