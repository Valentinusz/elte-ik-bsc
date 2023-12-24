import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPropertyChecker {
    @Test
    public void testCorrectTestClass() {
        assertTrue(BinaryPropertiesCheck.checkBinaryProperties(TestClass1.class, 1, 2 , 3));
    }
    @Test
    public void testIncorrectAnnotationUsageTestClass() {
        assertFalse(BinaryPropertiesCheck.checkBinaryProperties(TestClass2.class, 30, 60 , 5));
    }

    @Test
    public void testIncorrectParameterTestClass() {
        assertTrue(BinaryPropertiesCheck.checkBinaryProperties(TestClass3.class, 1, 2 , 3));
    }
}
