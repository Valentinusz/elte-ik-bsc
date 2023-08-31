import static org.junit.Assert.assertEquals;

// statikus import = statikus kontextus nélkül használható
import org.junit.Test;

public class MyMathTest {

    private static double EPSILON = 0.00001;

    // Tesztelésre szánt metódus
    @Test 
    public void twoToTheSecond() {
        // floatoknál nem működik túl jól az ==, hibahatárt használjunk
        //if (Math.abs(float1 -float2) < EPSILON)
        assertEquals(4, MyMath.power(2, 2), EPSILON);
    }

    @Test
    public void twoToZero() {
        assertEquals(1,MyMath.power(2, 0), EPSILON);
    }

    @Test
    public void twoToNegativeTwo() {
        assertEquals(0.25,MyMath.power(2, -2), EPSILON);
    }

    /*
    // Exceptiont kiváltó esetek tesztelése
    @Test (expected = IllegalArgumentException.class)
    public void exceptionTest() {
        MyMath.power(0, -10);
    }
    */

    /*
    javac -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" MyMathTest.java
    */

    /*
    java -cp ".;junit-4.12.jar;hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MyMathTest
    */
}