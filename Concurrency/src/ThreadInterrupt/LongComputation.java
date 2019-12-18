package ThreadInterrupt;


import java.math.BigInteger;

public class LongComputation {

    public static void main(String[] args){
        Thread t = new Thread(
                new LongComputationTask(
                    new BigInteger("200000"),
                    new BigInteger("100000000")
                )
            );

        //Daemon threads will not prevent the application from exiting if there are threads that are still running.
        //Once the main Thread ends, the application (and its daemon threads) will terminate.
        t.setDaemon(true);
        t.start();
        t.interrupt();
    }

    private static class LongComputationTask implements Runnable{
        BigInteger base;
        BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power){
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+" pow "+ this.power+ " = " + pow(this.base,this.power));
        }

        private BigInteger pow(BigInteger base, BigInteger power){
            BigInteger result = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0 ; i=i.add(BigInteger.ONE)){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
