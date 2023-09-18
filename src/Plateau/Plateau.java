package MarsRover.Plateau;

import MarsRover.Utilities.Utilities;

public
class Plateau {

    private final int xMin = 0;
    private final int yMin = 0;
    private static int xMax;
    private static int yMax;

    private
    Plateau(int xMax, int yMax) {
        Plateau.xMax = xMax;
        Plateau.yMax = yMax;
    }

    public static int getxMax() {return xMax;}
    public static int getyMax() {return yMax;}

    public static boolean establishThePlateu(int x, int y) {
        xMax = x;
        yMax = y;

        Utilities.goToSleep(250);
        System.out.println();
        System.out.println("Plateau created:  " + x + " " + y);
        System.out.println();
        Utilities.goToSleep(250);
        return true;
    }
}
