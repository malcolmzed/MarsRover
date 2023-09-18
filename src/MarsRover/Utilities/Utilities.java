package MarsRover.Utilities;

import java.awt.*;

public class Utilities {

    public static void goToSleep(int timeMiliSeconds) {
        try { Thread.sleep(timeMiliSeconds); }
        catch(InterruptedException e)
            { System.out.println("Sleep failed !!!"); }
    }

    public static void listFonts() {

    String[] F = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < F.length; i++) {
            System.out.println(F[i]);
        }
    }

}
