package speranskaya.com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        doCounterTask();
    }

    private void doCounterTask() {
        ExecutorService executor = Executors.newCachedThreadPool();
        final AtomicInteger conteiner = new AtomicInteger();
        for (int i = 0; i < 2000; i++) {
            executor.execute(new IncrementingTask(conteiner));
        }
        executor.shutdown();
        shutdownAndAwaitTermination(executor);

        System.out.println("counter = " + conteiner.get());

    }

    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
