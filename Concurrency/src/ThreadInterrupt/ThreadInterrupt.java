package ThreadInterrupt;

/**
 * AtomicReference that shows how er can interrupt a long ongoing thread.
 * Once created and running, we will force it to be interrupted and then the InterruptedException will
 * be executed in the interrupted thread.
 */
public class ThreadInterrupt {

    public static void main(String args[]){

        Thread thread= new Thread(new BlockingTask());
        thread.start();
        thread.interrupt();

    }

    private static class BlockingTask implements Runnable{

        @Override
        public void run() {
            try {
                //do things
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }
}
