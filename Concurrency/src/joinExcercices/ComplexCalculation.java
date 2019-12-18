package joinExcercices;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ComplexCalculation {

    public static void main(String[] args){
        ComplexCalculation calc = new ComplexCalculation();
        BigInteger result = calc.calculateResult(
                new BigInteger("2"),
                new BigInteger("3"),
                new BigInteger("5"),
                new BigInteger("2"));

        System.out.println(result);
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;

        PowerCalculatingThread t1 = new PowerCalculatingThread(base1,power1);
        PowerCalculatingThread t2 = new PowerCalculatingThread(base2,power2);

        t1.start();
        t2.start();

        //We will not force the app to stop at any time, it'll run for as long as it's needed
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = t1.getResult().add(t2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0 ; i=i.add(BigInteger.ONE)){
                this.result = this.result.multiply(base);

            }

        }

        public BigInteger getResult() { return result; }
    }
}