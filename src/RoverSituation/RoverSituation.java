package MarsRover.RoverSituation;

public
class RoverSituation {
    static int xPos;
    static int yPos;
    static int heading;    // 0, 90, 180 or 270

    public static int getxPos() {return xPos;}
    public static void setxPos(int xPos) {xPos = xPos;}

    public static int getyPos() {return yPos;}

    public static void setyPos(int yPos) {yPos = yPos;}

    public static int getHeading() {return heading;}

    public static void setHeading(char dir) {
        switch (dir) {
            case 'N': heading =   0; break;   // need to tidy this to use the enum
            case 'S': heading = 180; break;
            case 'E': heading =  90; break;
            case 'W': heading = 270; break;
        }
    }
}
