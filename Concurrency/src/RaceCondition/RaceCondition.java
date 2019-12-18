package RaceCondition;

/**
 * Race condition in a multi-threaded environment when more than one thread
 * try to access a shared resource (modify, write) at the same time. Multiple threads will hold a value, the value will
 * be modified by another thread in the meantime and, when the initial thread changes the value of the resource, it will
 * ignore the previous result of the other thread, leading to unexpected errors.
 * The sections in which more than one thread try to access and modify a shared resource is called 'critical section' and
 * needs to be accessed by up to one thread at a time. We do that by using locks (synchronized(lock)) or marking the
 * entire method as synchronized.
 */
public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread it = new IncrementingThread(inventoryCounter);
        DecrementingThread dt = new DecrementingThread(inventoryCounter);

        it.start();
        dt.start();

        it.join();
        dt.join();

        System.out.println("We currently have " + inventoryCounter.getItems() + " items");

    }

    public static class IncrementingThread extends Thread{

        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run(){
            for(int i = 0 ; i< 10000 ; i++){
                inventoryCounter.increment();
            }
        }
    }

    public static class DecrementingThread extends Thread{

        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run(){
            for(int i = 0 ; i< 10000 ; i++){
                inventoryCounter.decrement();
            }
        }
    }


    public static class InventoryCounter {
        private int items = 0;

        Object lock = new Object(); //The type of lock is irrelevant
        public void increment(){
            synchronized (lock){
                items++;
            }
        }

        public void decrement(){
            synchronized (lock){
                items--;
            }
        }

        public int getItems(){
            return items;
        }
    }
}
