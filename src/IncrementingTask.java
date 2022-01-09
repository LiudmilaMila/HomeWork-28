import java.util.concurrent.atomic.AtomicInteger;

public class IncrementingTask implements Runnable{

    private final AtomicInteger counter;

   public IncrementingTask(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.incrementAndGet();
    }
}
