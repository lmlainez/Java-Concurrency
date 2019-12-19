package Deadlock;

import java.util.Random;


/**
 * This is a solution to the DeadLock problem. In this case we are solving the problem by deleting the circular wait.
 * So, we will always lock roadA first and then roadB. Therefore there won't be any deadlock.
 */

public class DeadlockSolved {

    public static void main(String[] args){

        Intersection intersection = new Intersection();
        Thread threadTrainA = new Thread(new TrainA(intersection));
        Thread threadTrainB = new Thread(new TrainB(intersection));
        threadTrainA.start();
        threadTrainB.start();
    }

    public static class TrainA implements Runnable{

        private Intersection intersection;
        private Random random = new Random();
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
            synchronized (roadA){
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
                synchronized (roadB){
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
