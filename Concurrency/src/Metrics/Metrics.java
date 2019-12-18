package Metrics;


public class Metrics {

    private long count = 0;
    private volatile double average=0.0;

    public static void main(String[] args){

    }

    public synchronized void addSample(long sample){
        double currentSum = average*count;
        count++;
        average = (currentSum + sample) / count;
    }

    public double getAverage(){
        return average;
    }


}
