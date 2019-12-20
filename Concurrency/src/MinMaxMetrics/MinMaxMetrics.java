package MinMaxMetrics;

public class MinMaxMetrics {

    private long min;
    private long max;
    private Object lock;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
        lock =  new Object();
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        synchronized (lock){
            if(newSample > max){
                max = newSample;
            }else if(newSample < min){
                min = newSample;
            }
        }
        // Add code here
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return max;
    }
}
