package MarsRover;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class MarsRoverTest  {

    @Test
    public void checkPlateau() {
        MarsRover test001 = new MarsRover();
        MarsRover.establishPlateau(20,10);
        System.out.println("YES !!!");
        assertEquals("Hello", "NO");
    }
}
