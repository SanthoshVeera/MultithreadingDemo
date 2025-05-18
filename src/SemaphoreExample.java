import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i=0; i<10;i++) {
            System.out.println("Submitting Task: " + i);
            service.submit(new SemaphoreTask(semaphore));
        }

        service.shutdown();
        service.awaitTermination(5, TimeUnit.MINUTES);
    }
}


class SemaphoreTask implements Runnable {

    Semaphore semaphore;
    SemaphoreTask(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
     // Acquiring the permit
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // performing the task (Calling a HTTP service) for 2seconds
        try {
            System.out.println("Thread: " + Thread.currentThread().getName());
            Thread.sleep(2000);
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}