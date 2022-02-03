package DeadLockTask;

//        Воспроизвести проблему dead lock любым способом.
//
//        Например,две девочки Маша и Даша в детском саду делают аппликацию.
//        Для работы каждой нужны ножницы и цветная бумага.
//        Предположим,Маша взяла ножницы
//        (поток Маша вошла в монитор объекта ножницы),
//        а Даша бумагу(поток Даша вошла в монитор объекта бумага).
//        Каждая из них ждет другой предмет и не хочет делиться тем,что взяла.
//        Они не могут продолжить свою работу и будут ждать вечно.


public class DeadLockRisk implements Runnable {
    private static class Resource {
    }

    private final Resource scissors = new Resource();
    private final Resource paper = new Resource();

    public void doSun() {
        synchronized (scissors) { // May deadlock here
            System.out.println(Thread.currentThread().getName()
                    + " взяла ножницы для вырезания солнышка");
            synchronized (paper) {
                System.out.println(Thread.currentThread().getName()
                        + " взяла бумагу для вырезания солнышка");
                System.out.println(Thread.currentThread().getName()
                        + " вырезает солнышко");
            }
        }
    }

    public void doCloud() {
        synchronized (paper) { // May deadlock here
            System.out.println(Thread.currentThread().getName()
                    + " взяла бумагу для вырезания облачка");
            synchronized (scissors) {
                System.out.println(Thread.currentThread().getName()
                        + " взяла ножницы для вырезания облачка");
                System.out.println(Thread.currentThread().getName()
                        + " вырезает облачко");
            }
        }
    }

    public void run() {
        doSun();
        doCloud();
    }
}
