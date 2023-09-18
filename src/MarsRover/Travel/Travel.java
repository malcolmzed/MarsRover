package MarsRover.Travel;

import MarsRover.Journey.Journey;
import MarsRover.MarsRover;
import MarsRover.Plateau.Plateau;
import MarsRover.Compass.Compass;
import MarsRover.MarsGraphics.MarsGraphics;
import MarsRover.Utilities.Utilities;
import MarsRover.Rover.Rover;

import java.awt.*;

public
class Travel {

    static String message;

    static int plateauX = Plateau.getxMax();
    static int plateauY = Plateau.getyMax();

    public static
    void doTheJourney(Graphics g) {
        int plateauX = Plateau.getxMax();
        int plateauY = Plateau.getyMax();

        int heading = Rover.getHeading();
        int[] vector;

        int nowX = Rover.getPosX();
        int nowY = Rover.getPosY();

        int newX = Rover.getPosX();
        int newY = Rover.getPosY();

        showInitialSetup(g);

        String journey = Journey.getTheJourney();

        char movement;

        for (int i = 0; i < Journey.getTheJourney().length(); i++) {

            movement = Journey.getNextJourneyChar();
            MarsGraphics.DisplayJourney(g);

            heading = Rover.getHeading();
            nowX = Rover.getPosX();
            nowY = Rover.getPosY();

            switch(movement) {
                case 'M': {
                    vector = Compass.getVector();

                    // grey out current position and direction
                    MarsGraphics.drawLargeBox(g, nowX, nowY, Color.LIGHT_GRAY);
                    MarsGraphics.drawDirection(g, nowX, nowY,
                                    10, vector[0], -1 * vector[1], Color.LIGHT_GRAY);

                    newX = nowX + vector[0];
                    newY = nowY + vector[1];

                    if (newX < 0 || newX > plateauX - 1 || newY < 0 || newY > plateauY - 1) {
                        System.out.println("Cannot move - boundary");
                    }
                    else {
                        Rover.setPosX(newX);
                        Rover.setPosY(newY);

                        MarsGraphics.drawLargeBox(g, newX, newY, Color.RED);
                        MarsGraphics.drawDirection(g, newX, newY,0, vector[0], -1 * vector[1], Color.RED);
                    }
                    break;
                }
                case 'R': {
                    vector = Compass.getVector();
                    MarsGraphics.drawDirection(g, nowX, nowY,
                                       10, vector[0], -1 * vector[1], Color.LIGHT_GRAY);

                    heading = heading + 90;
                    if (heading == 360) heading = 0;

                    Rover.setHeading((char) heading);
                    vector = Compass.getVector();
                    MarsGraphics.drawDirection(g, nowX, nowY,
                                               10, vector[0], -1 * vector[1], Color.RED);

                    break;
                }
                case 'L': {
                    vector = Compass.getVector();
                    MarsGraphics.drawDirection(g, nowX, nowY,
                                               10, vector[0], -1 * vector[1], Color.LIGHT_GRAY);

                    heading = heading - 90;
                    if (heading == -90) heading = 270;

                    Rover.setHeading((char) heading);
                    vector = Compass.getVector();
                    MarsGraphics.drawDirection(g, nowX, nowY,
                                               10, vector[0], -1 * vector[1], Color.RED);
                    break;
                }
            }

            Utilities.goToSleep(500);

        }
    }

    private static
    void showInitialSetup(Graphics g) {
        int heading = Rover.getHeading();
        int startX = Rover.getPosX();
        int startY = Rover.getPosY();

        message =        "Plateau Size : "
                         + Plateau.getxMax()
                         + " "
                         + Plateau.getyMax()
                         + "   Initial Location : "
                         + startX
                         + " "
                         + startY
                         + "  Initial Heading : "
                         + heading
                         + "  "
                         + Compass.getDirection();

        MarsGraphics.ShowText(g, message, Color.BLACK, 2, -2, 18);
        Utilities.goToSleep(2000);
        MarsGraphics.ShowText(g, message, Color.WHITE, 2, -2, 18);

        MarsGraphics.drawLargeBox(g, startX, startY, Color.RED);

        int[] vector = Compass.getVector();
        MarsGraphics.drawDirection(g, startX, startY, 10, vector[0], -1 * vector[1], Color.RED);

        MarsGraphics.ShowText(g, "Let's start the journey . . .", Color.BLACK, 20, -2, 18);
        Utilities.goToSleep(2000);

        MarsGraphics.DisplayJourney(g);

        Utilities.goToSleep(1000);
        MarsGraphics.ShowText(g, "Let's start the journey . . .", Color.WHITE, 20, -2, 18);

        }
    }

