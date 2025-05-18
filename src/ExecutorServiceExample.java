import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);

        for(int i=0; i<6; i++) {
            System.out.println("Submitting task: " + i);
            service.submit(new Task());
        }

        service.shutdown();
        service.awaitTermination(4, TimeUnit.MINUTES);
    }
}


class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
    }
}
