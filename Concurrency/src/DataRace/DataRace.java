package DataRace;

/**
 * Often the compiler and the CPU may execute the instructions out of order to optimize performance and utilization.
 * As X and Y are not co-dependents (the value of x doesn't depend on y and viceversa), so they are candidates for
 * re-arrangement.
 * One way to protect this is synchronize or volatile.
 * If we use synchronized in the method we won't have Data Race at all as only one thread is able to access the method at
 * the same time, and re-arrangement won't happen.
 * Using volatile in a shared variable, we make sure that all instructions before the access to the volatile variable are
 * executed before and all instructions after are also executed after.
 */
public class DataRace {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }

        });

        thread1.start();
        thread2.start();
    }

    public static class SharedClass {
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }
}
