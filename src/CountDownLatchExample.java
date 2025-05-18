import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService service = Executors.newFixedThreadPool(3);

        service.submit(new CDLETask(latch, 2000));
        service.submit(new CDLETask(latch, 3000));
        service.submit(new CDLETask(latch, 4000));

        service.shutdown();
        service.awaitTermination(5, TimeUnit.MINUTES);

        latch.await();
        System.out.println("Done with 3 tasks");
    }
}

class CDLETask implements Runnable {
    CountDownLatch countDownLatch;
    long timeSpent;
    CDLETask(CountDownLatch countDownLatch, long timeSpent) {
        this.countDownLatch = countDownLatch;
        this.timeSpent = timeSpent;
    }


    @Override
    public void run() {
        try {
//        countDownLatch.await();
        System.out.println("Started Thread: " + Thread.currentThread().getName());
       // System.out.println(countDownLatch.getCount());

        Thread.sleep(timeSpent);
        countDownLatch.countDown();
       System.out.println("Executed task" + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
