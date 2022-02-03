package сountDownLatchTask;

import java.util.concurrent.CountDownLatch;

public class WorkerRunnable implements Runnable{
    private final CountDownLatch doneSignal;

    public WorkerRunnable(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doneSignal.countDown();
    }
}
