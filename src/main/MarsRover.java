package MarsRover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public
class MarsRover {

    public static
    class State {
        int sizeX;
        int sizeY;
        int posX;
        int posY;
        int heading;

        State(
                int sX, int sY,
                int pX, int pY,
                int h

        ) {
            sX = sizeX;
            sY = sizeY;
            pX = posX;
            pY = posY;
            h = heading;
        }
    }

    public static State currentState = new State(0, 0, 0, 0, 0);

    public static
    class vector {
        int x;
        int y;

        vector(int xx,
               int yy) {
            xx = x;
            yy = y;
        }
    }

    public static
    class HistoryRow {
        int sequence, x, y, heading;
        String message;

        HistoryRow(int sequence, int x, int y, int heading, String message) {
            this.sequence = sequence;
            this.x = x;
            this.y = y;
            this.heading = heading;
            this.message = message;
        }
    }


    private static vector moveVector = new vector(0, 0);

    public static
    boolean establishPlateau(int x, int y) {
        if (x < 1 || y < 1) {
            System.out.println("Invalid plateau dimensions");
            return false;
        }

        MarsRover.currentState.sizeX = x;
        MarsRover.currentState.sizeY = y;
        return true;
    }

    public static
    boolean establishInitialState(int x, int y, char direction) {

        if (x < 0 || y < 0
            || x > MarsRover.currentState.sizeX
            || y > MarsRover.currentState.sizeY) {
            System.out.println("Invalid initial location");
            return false;
        } else {
            MarsRover.currentState.posX = x;
            MarsRover.currentState.posY = y;

            boolean result = true;   // assume success

            switch (direction) {
                case 'N': {
                    MarsRover.currentState.heading = 0;
                    break;
                }
                case 'S': {
                    MarsRover.currentState.heading = 180;
                    break;
                }
                case 'E': {
                    MarsRover.currentState.heading = 90;
                    break;
                }
                case 'W': {
                    MarsRover.currentState.heading = 270;
                    break;
                }
                default: {
                    System.out.println(direction + "  Invalid initial direction");
                    result = false;
                }
            }
            setHeading();
            return result;
        }
    }

    public static
    void setHeading() {
        switch (MarsRover.currentState.heading) {
            case 0:
                MarsRover.moveVector.x = 0;
                MarsRover.moveVector.y = 1;
                break;
            case 90:
                MarsRover.moveVector.x = 1;
                MarsRover.moveVector.y = 0;
                break;
            case 180:
                MarsRover.moveVector.x = 0;
                MarsRover.moveVector.y = -1;
                break;
            case 270:
                MarsRover.moveVector.x = -1;
                MarsRover.moveVector.y = 0;
                break;
            default:
                System.out.println("Shit " + MarsRover.currentState.heading);
                break;
        }
        return;
    }

    public static
    void rotateLeft() {
        MarsRover.currentState.heading = MarsRover.currentState.heading - 90;
        if (MarsRover.currentState.heading == -90) MarsRover.currentState.heading = 270;
        setHeading();
        return;
    }

    public static
    void rotateRight() {
        MarsRover.currentState.heading = MarsRover.currentState.heading + 90;
        if (MarsRover.currentState.heading == 360) MarsRover.currentState.heading = 0;
        setHeading();
        return;
    }

    public static
    boolean move() {
        int newX, newY;

        newX = MarsRover.currentState.posX + MarsRover.moveVector.x;
        newY = MarsRover.currentState.posY + MarsRover.moveVector.y;

        if (newX < 0 || newY < 0 ||
            newX > MarsRover.currentState.sizeX ||
            newY > MarsRover.currentState.sizeY) {
            System.out.println("Edge constraint - could not move");
            return false;
        }

        MarsRover.currentState.posX = newX;
        MarsRover.currentState.posY = newY;
        return true;
    }


    public static
    int processLetter(char theLetter) {
        System.out.println(
                MarsRover.currentState.posX + " " +
                MarsRover.currentState.posY + " " +
                MarsRover.currentState.heading + " "
        );

        switch (theLetter) {
            case 'M':
                move();
            case 'L':
                rotateLeft();
            case 'R':
                rotateRight();
        }

        System.out.println(
                theLetter + " " +
                MarsRover.currentState.posX + " " +
                MarsRover.currentState.posY + " " +
                MarsRover.currentState.heading + " "
        );

        System.out.println("----------------------");

        return 1;
    }

    public static
    void main(String[] args) {

        String theJourney;

        theJourney = "MMRMMLMM";

        ArrayList<HistoryRow> history = new ArrayList<HistoryRow>();

        history.add(new HistoryRow(1, 3, 3, 270, "OK1"));

        if (!establishPlateau(10, 10)) System.exit(-1);
        System.out.println(MarsRover.currentState.sizeX + " " + MarsRover.currentState.sizeY);

        if (!establishInitialState(4, 4, 'S')) System.exit(-2);
        System.out.println(MarsRover.currentState.posX + " " + MarsRover.currentState.posY);

        System.out.println(">>>>>>>>>>>>>>>>");

        for (int i = 0; i < theJourney.length(); i++) {
            processLetter(theJourney.charAt(i));
        }
        return;
    }
}
