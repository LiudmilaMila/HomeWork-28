package сountDownLatchTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DownLatchMain {
    public static void main(String[] args) {


        CountDownLatch doneSignal = new CountDownLatch(2000);
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 2000; i++) {
            executor.execute(new WorkerRunnable(doneSignal));
        }
        try {
            doneSignal.await();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("Задачи завершены.");
    }
}
