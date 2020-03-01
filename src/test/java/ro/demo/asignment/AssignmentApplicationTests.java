package ro.demo.asignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        AssignmentApplication.class,
        H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
public class AssignmentApplicationTests {

    @Test
    public void contextLoads() {
    }

}
