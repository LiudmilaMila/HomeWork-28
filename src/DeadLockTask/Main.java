package DeadLockTask;

public class Main {
    public static void main(String[] args) {
        new Main().run();

    }

    private void run() {
        new Main().run();
        DeadLockRisk job = new DeadLockRisk();
        Thread masha = new Thread(job, "Маша");
        Thread dasha = new Thread(job, "Даша");
        masha.start();
        dasha.start();
    }
}
