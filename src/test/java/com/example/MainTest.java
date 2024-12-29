
import org.junit.jupiter.api.*;
import org.example.Main;

public class MainTest{
    @Test
    public void testRight() {
        Assertions.assertTrue(Main.henlo() == "henlo");
    }

    @Disabled("Just test")
    @Test
    public void testWrong() {
        Assertions.assertTrue(Main.henlo() == "hanlo");
    }
}
