import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FactorialExecutorservice {
    public static void main(String[] args) throws InterruptedException {

        long initTime = System.currentTimeMillis();
        List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> bigList = Arrays.asList(10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyTask[] myTasks = new MyTask[10];

        for( int i=0; i<10; i++) {
            myTasks[i] = new MyTask(bigList.get(i));
            executorService.submit(myTasks[i]);
        }

        executorService.awaitTermination(10, TimeUnit.SECONDS);

        for (MyTask myTask: myTasks ) {
            System.out.println("Factorial of "+ myTask.number + " is: " + myTask.result);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken-"+ (endTime-initTime));
    }
}

class MyTask implements Runnable {
        int number;
        BigInteger result = BigInteger.ONE;

        MyTask(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            this.result = calculateFactorial(this.number);
        }

        public static BigInteger calculateFactorial(int number) {
            BigInteger result = BigInteger.ONE;
            for(int i=1; i<=number; i++)
            {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
}

