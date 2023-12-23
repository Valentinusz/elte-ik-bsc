import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Fib extends RecursiveTask<Long> {
    private final int n;

    public Fib(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n < 2) {
            return 1L;
        } else {
            Fib nMinus1 = new Fib(n-1);
            Fib nMinus2 = new Fib(n-2);

            nMinus1.fork();
            nMinus2.fork();

            return nMinus1.join() + nMinus2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(new Fib(45));
        System.out.println(forkJoinTask.join());
    }
}
