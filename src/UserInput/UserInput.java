package MarsRover.UserInput;

import MarsRover.Journey.Journey;
import MarsRover.Plateau.Plateau;
import MarsRover.MarsGraphics.MarsGraphics;
import MarsRover.Utilities.Utilities;
import MarsRover.Rover.Rover;

import java.util.Scanner;

public
class UserInput {
    static Scanner input = new Scanner(System.in);

    private static
    void invalidInputMessage() {
        System.out.println();
        System.out.println("    Invalid input - please try again");
        System.out.println();
        Utilities.goToSleep(1500);
    }

    public static
    boolean getAndSetJourney() {

        boolean validInput = false;
        String userJourney = "";

        while (!validInput) {
            validInput = true;

            System.out.println("Please enter the journey using the letters:");
            System.out.println("   M    Move to next cell in current direction");
            System.out.println("   L    Rotate Left  (anticlockwise)");
            System.out.println("   R    Rotate Right (clockwise)");
            System.out.println("e.g, 'MMMRMMLMMRRMM'");

            userJourney = input.nextLine();
            String text = userJourney;

            text = text.replace("M", "");
            text = text.replace("L", "");   // if user entered a valid journey then text
            text = text.replace("R", "");   // will now be an empty string

            if (text.length() > 0) {invalidInputMessage(); validInput = false;}
        }
        MarsRover.Journey.Journey.setJourney(userJourney);
        return false;
    }

    public static
    boolean getAndSetInitialState() {

        boolean validInput = false;

        int x=-1, y=-1;

        while (!validInput) {
            validInput = true;

            int xMax = MarsRover.Plateau.Plateau.getxMax() - 1;
            int yMax = MarsRover.Plateau.Plateau.getyMax() - 1;

            System.out.println("Please enter the inital state");
            System.out.println("   Position between 0 0 and " + xMax + " " + yMax);
            System.out.println("   Direction N S E or W");
            System.out.println("   , e.g, '12 7 E'");

            String text = input.nextLine();

            String[] parts = text.split(" ");

            //validation
            if (parts.length != 3) validInput = false;
            else {
                try {
                    x =  Integer.parseInt(parts[0]) ;
                } catch (NumberFormatException e) {
                    validInput = false;
                }

                try {
                    y =  Integer.parseInt(parts[1]) ;

                } catch (NumberFormatException e) {
                    validInput = false;
                }

                if ( /* still */ validInput)
                    if (x < 0 || x > xMax || y < 0 || y > yMax) {
                        System.out.printf("%n    Entered position is outside the plateau");
                        Utilities.goToSleep(1500);
                        validInput = false;
                    }

                char dirC = 'x';

                if (parts[2].length() != 1) validInput = false;
                else {
                    dirC = parts[2].charAt(0);
                    String dirS = parts[2].substring(0, 1);
                    if (!"NSEW".contains(dirS)) validInput = false;
                }

                if (validInput) {
                    Rover.setPosX(x-1);
                    Rover.setPosY(y-1);
                    Rover.setHeadingFromDirection(dirC);
                    return true;
                } else invalidInputMessage();
            }
        }
        return false;
    }

    public static boolean getAndSetPlateau() {

        boolean validInput = false;

        while (!validInput) {
            validInput = true;

            System.out.println("Please enter the plateau dimensions");
            System.out.println("   between 0 0 and 25 25");
            System.out.println("     e.g, '6 13'");

            String text = input.nextLine();

            String[] parts = text.split(" ");

            //validation
            int width = 0, height = 0;

            if (parts.length != 2) validInput = false;
            else {
                try {
                    width = Integer.parseInt(parts[0]);
                } catch (NumberFormatException e) {
                    validInput = false;
                }

                try {
                    height = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    validInput = false;
                }

                if ( /* still */ validInput)
                    if (width < 0 || width > 25 || height < 0 || height > 25) {
                        validInput = false;
                    }
            }

            if (validInput) {
                MarsRover.Plateau.Plateau.establishThePlateu(width, height);
                return true;
            } else invalidInputMessage();
        }
        return validInput;
    }
}

