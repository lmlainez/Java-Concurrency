package Deadlock;

import java.util.Random;

/**
 * Deadlock is the situation in which multiple threads share the same resources and prevent eachother from accessing
 * the needed resources in order to advance. Therefore none of the threads can terminate and liberate the resources and
 * the application ceases to function.
 * This happens when all the following conditions are met:
 *  1 - Only one thread can have exclusive access to a resource. (Mutual exclusion).
 *  2- The thread holds the resource and waits for another. (Hold and Wait).
 *  3- A resource is released only when a thread is done using it. (Non-preemtive allocation).
 *  4- A chain of more than one thread holding one resource and waiting for another. (Circular wait).
 */

public class Deadlock {

    public static void main(String[] args){

        Intersection intersection = new Intersection();
        Thread threadTrainA = new Thread(new TrainA(intersection));
        Thread threadTrainB = new Thread(new TrainB(intersection));
        threadTrainA.start();
        threadTrainB.start();
    }

    public static class TrainA implements Runnable{

        private Intersection intersection;
        private Random  random = new Random();
        public TrainA (Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run(){
            while(true){
                long sleepingTime =random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                    intersection.takeRoadA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static class TrainB implements Runnable{

        private Intersection intersection;
        private Random  random = new Random();
        public TrainB (Intersection intersection){
            this.intersection = intersection;
        }

        @Override
        public void run(){
            while(true){
                long sleepingTime =random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                    intersection.takeRoadB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public static class Intersection{

        private Object roadA =  new Object();
        private Object roadB =  new Object();


        public void takeRoadA(){
            synchronized (roadA){
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
                synchronized (roadB){
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void takeRoadB(){
            synchronized (roadB){
                System.out.println("Road B is locked by thread " + Thread.currentThread().getName());
                synchronized (roadA){
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
