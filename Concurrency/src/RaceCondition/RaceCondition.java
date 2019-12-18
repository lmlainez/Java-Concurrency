package RaceCondition;

/**
 * Created by developer on 18/12/19.
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
