import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
//        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
//        Future<?> future = forkJoinPool.submit(() -> {
//            System.out.println(Thread.currentThread().getName() + ": Hi");
//        });
//
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
//
//        Future<?> future = forkJoinPool.submit(() -> {
//            System.out.println(Thread.currentThread().getName() + ": Hi");
//        });
//
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
        Future<?> future = forkJoinPool.submit(new MyAction(new long[1000], 0, 1000));

        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    private static class MyAction extends RecursiveAction {
        private final long[] values;
        private final int start;
        private final int end;

        public MyAction(long[] values, int start, int end) {
            this.values = values;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (this.end - this.start < 10) {
                System.out.println("::::::::::::::::::::::");
                for (int i = start; i < end; i++) {
                    this.values[i] = (long)(i) * i * i;
                    System.out.println(Thread.currentThread().getName() + "\t values[" + i  +"] " + values[i]);
                }
            } else {
                int midPoint = (start + end) / 2;

                MyAction ma1 = new MyAction(values, start, midPoint);
                MyAction ma2 = new MyAction(values, midPoint, end);

                ma1.fork();
                ma2.fork();

                ma1.join();
                ma2.join();
            }
        }
    }
}
