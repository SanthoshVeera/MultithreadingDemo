import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

class ArraySumCalculator extends RecursiveTask<Integer> {

    int arr[];
    int start;
    int end;

    int threshold = 100;
    static AtomicInteger forkCount = new AtomicInteger(0);

    ArraySumCalculator(int[] inputArray, int start, int end) {
        this.arr = inputArray;
        this.start = start;
        this.end = end;
        System.out.println("Inside Thread: " + Thread.currentThread().getName());
    }

    @Override
    protected Integer compute() {
//        System.out.println("Inside Thread: " + Thread.currentThread().getName());
        if(end - start <= threshold)
        {
            int result = 0;
            for(int i=start; i< end; i++) {
                result += arr[i];
            }
            return result;
        } else {
            int mid = (start + end) / 2;
            ArraySumCalculator left = new ArraySumCalculator(arr,start, mid);
            ArraySumCalculator right = new ArraySumCalculator(arr,mid, end);

            left.fork();
            forkCount.incrementAndGet();
            right.fork();
            forkCount.incrementAndGet();
            return left.join() + right.join();
        }
    }

    public static int getForkCount() {
        return forkCount.get();
    }
}



public class ForkJoinExample {
    public static void main(String[] args) {

        int array[] = new int[1000];

        for(int i=0; i< array.length; i++){
            array[i] = i+1;
        }

        System.out.println("Initialized array");

        ArraySumCalculator arraySumCalculator = new ArraySumCalculator(array,0,array.length);
        int finalResult = arraySumCalculator.compute();


        System.out.println("Final Result: "+ finalResult);
        System.out.println("Number of Forks: " + arraySumCalculator.forkCount.get());
    }
}
