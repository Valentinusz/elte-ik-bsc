package classdumper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class ClassDumperTest {
    @ParameterizedTest
    @CsvFileSource(files = "classdumper/test.csv", numLinesToSkip = 1)
    void test(String className, String expected) throws ClassNotFoundException {
        assertEquals(expected, ClassDumper.dump(Class.forName("classdumper." + className)));
    }
}