package MarsRover.Rover;

public
class Rover {
    static int  posX;
    static int  posY;
    static int  heading;

    public static int getPosX() {return posX;}
    public static int getPosY() {return posY;}
    public static int getHeading() {return heading;}

    public static
    void setPosX(int x) {
        posX = x;
    }

    public static
    void setPosY(int y) {
        posY = y;
    }

    public static
    void setHeading(int inHeading) {
        heading = inHeading;
    }

    public static
    void setHeadingFromDirection(char dir) {
        switch(dir) {
            case 'N' : heading =   0; break;
            case 'S' : heading = 180; break;
            case 'E' : heading =  90; break;
            case 'W' : heading = 270; break;
        }
    }
}
