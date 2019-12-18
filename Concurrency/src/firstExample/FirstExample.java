package firstExample;

public class FirstExample {

    public static void main(String[] args) throws InterruptedException {
        Thread thread =  new Thread(new Runnable(){
           @Override
            public void run(){
               System.out.println("We are now in thread: " + Thread.currentThread().getName());
               System.out.println("Current Thread priority: " + Thread.currentThread().getPriority());
               throw new RuntimeException("Intentional Error");

           }
        });
        //This is a global handler for all errors that come from the thread and are not caught inside it.
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in Thread " +t.getName()+", the error" +
                        "is " + e.getMessage());
            }
        });
        thread.setName("New working thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("We are in thread: " + Thread.currentThread().getName()+" before starting a new Thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName()+" after starting a new Thread");
        Thread.sleep(10000);
        System.out.println("End of firstExample thread: " + Thread.currentThread().getName());

    }
}
