package ro.demo.asignment.foo;
import org.junit.Test;
import static org.junit.Assert.*;

public class Hello33Test {
    private static final int MULTIPLIER;
    static {
        String multiplier = System.getenv("MULTIPLIER");
        MULTIPLIER = multiplier != null ? Integer.parseInt(multiplier) : 1;
    }
    @Test public void one() {
        if (Math.random() < 0.015) {
            fail("oops");
        }
    }
    @Test public void two() {}
    @Test public void three() throws Exception {
        Thread.sleep(330 * MULTIPLIER);
    }
    @Test public void four() throws Exception {
        Thread.sleep(1000 * MULTIPLIER);
    }
}
