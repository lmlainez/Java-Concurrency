package RaceCondition;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * In this example of Race condition we will be using AtomicInteger, which, if we use its methods, they are  turned into
 * atomic operations. Therefore the methods increment and decrement will be atomic and we won't need to lock or
 * synchronize the methods. This class is great to implement metrics and counters.
 */
public class RaceConditionWithAtomicInteger {

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
        private AtomicInteger items = new AtomicInteger(0);

        public void increment(){
                items.incrementAndGet();
        }

        public void decrement(){
            items.decrementAndGet();
        }

        public AtomicInteger getItems(){
            return items;
        }
    }
}
