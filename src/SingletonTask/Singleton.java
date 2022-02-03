package SingletonTask;

import java.util.concurrent.atomic.AtomicInteger;

public class Singleton  {
    private static Singleton instance;
    private static final AtomicInteger instanceCount = new AtomicInteger();

    private Singleton() {
    instanceCount.incrementAndGet();
    }
    public static  synchronized Singleton getInstance() {
        if (instance == null) { // ленивая “lazy” инициализация
            instance = new Singleton();
        }
        return instance;
    }

    public static AtomicInteger getInstanceCount() {
        return instanceCount;
    }
}
