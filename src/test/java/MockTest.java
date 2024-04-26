import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockTest {

    @Test
    void mockTest(){
        var a = 2;
        var b = 3;

        Assertions.assertEquals(5, a+b);
    }
}
