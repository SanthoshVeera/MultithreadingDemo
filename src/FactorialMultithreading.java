import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialMultithreading {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10);
        List<Integer> bigList = Arrays.asList(10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000);
        List<BigInteger> results = new ArrayList<BigInteger>();
        MyThread1 myThread1Array[] = new MyThread1[10];

        for(int i=0; i< bigList.size(); i++) {
            myThread1Array[i] = new MyThread1(bigList.get(i));
            myThread1Array[i].start();
        }

        for(int i=0; i< bigList.size(); i++) {
            myThread1Array[i].join();
            System.out.println("Factorial of "+ bigList.get(i)+ " is: "+myThread1Array[i].result);
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: "+ (end - start));
    }
}

class MyThread1 extends Thread {
    private int number;
    BigInteger result;


    MyThread1(int number){
        super();
        this.number = number;
    }

    @Override
    public void run() {
//        System.out.println("Inside thread: "+ Thread.currentThread());
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
