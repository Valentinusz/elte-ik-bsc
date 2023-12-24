package either;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Supplier;

class EitherTest {
    @Test
    void left() {
        Either<String, Integer> stringIntegerEither = Either.left("test");
        assertNotNull(stringIntegerEither);
    }

    @Test
    void right() {
        Either<String, Integer> stringIntegerEither = Either.right(5);
        assertNotNull(stringIntegerEither);
    }

    @Test
    void iterateLeft() {
        assertEquals("test", Either.iterate(Either.left("test"), x -> (x + 1), 5));
    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -5, 0})
    void nonPositiveIterationCount(int iter) {
        assertEquals(Either.iterate(Either.right(5), x -> (x + 1), iter), 5);
    }

    @Test
    void positiveIterationCount() {
        assertEquals(8, Either.iterate(Either.right(5), x -> (x + 1), 3));
    }

    @Test
    void swap() {
        Either<String, Integer> stringIntegerEither = Either.left("test");
        assertEquals("test", stringIntegerEither.swap().getRight());
    }

    @Test
    void isLeft() {
        Either<String, Integer> stringIntegerEither = Either.left("test");
        assertTrue(stringIntegerEither.isLeft());
        assertFalse(stringIntegerEither.isRight());
    }

    @Test
    void isRight() {
        Either<String, Integer> stringIntegerEither = Either.right(5);
        assertFalse(stringIntegerEither.isLeft());
        assertTrue(stringIntegerEither.isRight());
    }

    @Test
    void getLeft() {
        Either<String, Integer> stringIntegerEither = Either.left("test");
        assertEquals("test", stringIntegerEither.getLeft());
    }

    @Test
    void getLeftWhileRight() {
        Either<String, Integer> stringIntegerEither = Either.right(5);
        assertThrows(NoSuchElementException.class, stringIntegerEither::getLeft);
    }

    @Test
    void getRight() {
        Either<String, Integer> stringIntegerEither = Either.right(5);
        assertEquals(5, stringIntegerEither.getRight());
    }

    @Test
    void getRightWhileLeft() {
        Either<String, Integer> stringIntegerEither = Either.left("test");
        assertThrows(NoSuchElementException.class, stringIntegerEither::getRight);
    }

    @Test
    void orElseGet() {
        Either<String, Integer> left = Either.left("test");
        Either<String, Integer> right = Either.right(5);
        Supplier<Integer> supplier = () -> 10;

        assertAll(
                () -> assertEquals(10, left.orElseGet(supplier)),
                () -> assertEquals(5, right.orElseGet(supplier))
        );
    }

    @Test
    void map() {
        Either<String, Integer> left = Either.left("test");
        Either<String, Integer> right = Either.right(5);
        Function<Integer, DayOfWeek> function = DayOfWeek::of;

        assertAll(
                () -> assertEquals("test", left.map(function).getLeft()),
                () -> assertEquals(DayOfWeek.FRIDAY, right.map(function).getRight())
        );
    }

    @Test
    void bind() {
        String noSuchDayMessage = "Nincs ilyen nap";

        Either<String, Integer> left = Either.left("test");
        Either<String, Integer> right = Either.right(5);
        Either<String, Integer> rightButWrong = Either.right(8);
        Function<Integer, Either<String, DayOfWeek>> function = x -> {
            try {
                return Either.right(DayOfWeek.of(x));
            } catch (DateTimeException e) {
                return Either.left(noSuchDayMessage);
            }
        };

        assertAll(
                () -> assertEquals("test", left.bind(function).getLeft()),
                () -> assertEquals(DayOfWeek.FRIDAY, right.bind(function).getRight()),
                () -> assertThrows(NoSuchElementException.class, () -> rightButWrong.bind(function).getRight()),
                () -> assertEquals(noSuchDayMessage, rightButWrong.bind(function).getLeft())
        );
    }
}