package either;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Either<L, R> {
    private final L left;

    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L1, R1> Either<L1, R1> left(L1 left) {
        return new Either<>(left, null);
    }

    public static <L1, R1> Either<L1, R1> right(R1 right) {
        return new Either<>(null, right);
    }

    public static <R1> R1 iterate(Either<R1, R1> either, UnaryOperator<R1> function, int n) {
        if (either.isLeft()) {
            return either.getLeft();
        }

        R1 value = either.getRight();

        for (int i = 0; i < n; i++) {
            value = function.apply(value);
        }

        return value;
    }

    public Either<R, L> swap() {
        if (isLeft()) {
            return new Either<>(null, this.getLeft());
        }
        return new Either<>(this.getRight(), null);
    }

    public boolean isLeft() {
        return this.left != null;
    }

    public boolean isRight() {
        return this.right != null;
    }

    public L getLeft() {
        if (this.isLeft()) {
            return this.left;
        }
        throw new NoSuchElementException();
    }

    public R getRight() {
        if (this.isRight()) {
            return this.right;
        }
        throw new NoSuchElementException();
    }

    public R orElseGet(Supplier<R> other) {
        if (this.isRight()) {
            return this.getRight();
        }
        return other.get();
    }

    public <T> Either<L, T> map(Function<R, T> function) {
        if (this.isRight()) {
            return new Either<>(null, function.apply(this.getRight()));
        }
        return new Either<>(this.getLeft(), null);
    }

    public <T> Either<L, T> bind(Function<R, Either<L, T>> function) {
        if (this.isRight()) {
            return function.apply(this.getRight());
        }
        return new Either<>(this.getLeft(), null);
    }
}
