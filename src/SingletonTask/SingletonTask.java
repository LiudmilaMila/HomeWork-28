package SingletonTask;

import java.util.concurrent.CountDownLatch;

public class SingletonTask implements Runnable {
    private final CountDownLatch startSignal;

    private final CountDownLatch doneSignal;

    public SingletonTask(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }


    @Override
    public void run() {
        try {
            startSignal.await();
            Singleton.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        doneSignal.countDown();
    }

}
