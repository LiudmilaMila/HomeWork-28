package SingletonTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        final int countOfTasks = 1000;
        CountDownLatch doneSignal = new CountDownLatch(countOfTasks);
        CountDownLatch startSignal = new CountDownLatch(1);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < countOfTasks; i++) {
            executor.submit(new SingletonTask(startSignal, doneSignal));
        }

        startSignal.countDown();
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println(Singleton.getInstanceCount());
    }
}
