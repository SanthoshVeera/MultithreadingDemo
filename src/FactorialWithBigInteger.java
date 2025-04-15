import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class FactorialWithBigInteger {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> bigList = Arrays.asList(10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000);
        bigList.forEach(x -> {
            System.out.println("Factorial of " + x +" is " + calculateFactorial(x));
        });
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
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
