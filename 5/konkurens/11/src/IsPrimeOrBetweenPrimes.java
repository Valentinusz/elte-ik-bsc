import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class IsPrimeOrBetweenPrimes extends RecursiveTask<Boolean> {
    private final int n;

    public IsPrimeOrBetweenPrimes(int n) {
        this.n = n;
    }

    @Override
    protected Boolean compute() {
        if (n < 2) {
            return false;
        }

        if (n % 2 == 1) {
            for (int i = 2; i < n-1; i++) {
                if (n % i == 0) {
                    return  false;
                }
            }
            return true;
        }

        if (n == 2) {
            return true;
        }

        IsPrimeOrBetweenPrimes task1 = new IsPrimeOrBetweenPrimes(n-1);
        IsPrimeOrBetweenPrimes task2 = new IsPrimeOrBetweenPrimes(n+1);

        task1.fork();
        task2.fork();

        return task1.join() && task2.join();
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

        ForkJoinTask<Boolean> forkJoinTask = forkJoinPool.submit(new IsPrimeOrBetweenPrimes(12));
        System.out.println(forkJoinTask.join());
    }
}
