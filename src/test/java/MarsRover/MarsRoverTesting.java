package MarsRover;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public
class MarsRoverTesting {

    public List<String> allLines;

    String tab = "\t";
    String space = " ";


    public void getFile(String theFile) {
        try {
            allLines = Files.readAllLines(Paths.get(theFile));
        } catch (IOException e) {
            System.out.println("File not found : " + theFile);
            java.lang.System.exit(-1);
        }
    }

        public void readFile(String filename) {
        //String fileName = "MarsRoverTestData.txt";
                getFile(filename);
        System.out.println("readFile>> " + filename);

        for (String line : allLines) {

            String newLine = line.replaceAll(tab, " ");
            newLine = newLine.trim();

            String stripped = line.trim();
            stripped = line.replaceAll(tab, "");
            stripped = line.replaceAll(space, "");

            String first50 = String.format("%" + (-50) + "s", newLine);

            if (stripped.length() == 0) {
                continue;  // ignore blank lines
            }

            if (newLine.length()>1 && newLine.substring(0,2).equals("##")) {
                continue; //don't even echo comments which begin ##
            }

            if (newLine.charAt(0) == '#') {
                System.out.println (first50);
                continue;  // print comments which start with a single #
            }

            System.out.printf ("%s%n", newLine);

            String[] elements = newLine.split("\\s+");

            switch ( elements[0]) {
                case "P": {
                    establishAndcheckThePlateau( Integer.valueOf(elements[1]),
                                                 Integer.valueOf(elements[2])
                                               );
                    break;
                }
                case "I": {
                    System.out.println("establishAndCheckInitialState");
                    establishAndCheckInitialState( Integer.valueOf(elements[1]),
                                                   Integer.valueOf(elements[2]),
                                                   elements[3]
                                                  );
                    break;
                }
                case "J": {
                    System.out.println("Journey");
                    establishAndCheckJourney(elements[1],
                                             Integer.valueOf(elements[2]),
                                             Integer.valueOf(elements[3]),
                                             elements[4]
                                             );
                    break;
                }
                default:  System.out.println("Invalid code: " + elements[0]);
            }
        }
    }

    public
    void establishAndcheckThePlateau(int xDim, int yDim) {

        boolean returnVal;
        returnVal = MarsRover.establishPlateau(xDim, yDim);
        System.out.println("Plateau Okay: " + returnVal);
        System.out.println();

        if (returnVal == false)
            throw new AssertionError("establishPlateau failed");
        else {
            assertAll(
                () -> assertEquals(xDim, MarsRover.currentState.sizeX),
                () -> assertEquals(yDim, MarsRover.currentState.sizeY)
            );
        }
    }

    //@Test
    public void plateauTest() {
        String fileName = "checkPlateau.txt";  // in the local repository
        readFile(fileName);
    }

    public
    void  establishAndCheckInitialState( int xPos, int yPos, String dir) {

        boolean returnVal;
        returnVal = MarsRover.establishInitialState(xPos, yPos, dir.charAt(0));

        System.out.println("Initial State Okay: " + returnVal);
        System.out.println();

        int theHeading = -1;

        if (returnVal == false)
            throw new AssertionError("establishAndCheckInitialState failed");
        else {
            switch (dir) {
                case "N": theHeading =   0; break;
                case "S": theHeading = 180; break;
                case "E": theHeading =  90; break;
                case "W": theHeading = 270; break;
            }

            int headingError = theHeading - MarsRover.currentState.heading;

            assertAll(
                    () -> assertEquals(xPos,     MarsRover.currentState.posX),
                    () -> assertEquals(yPos,     MarsRover.currentState.posY),
                    () -> assertEquals(headingError,  0)
            );
        }
    }

    //@Test
    public void initialStateTest() {
        String fileName = "checkInitialState.txt";  // in the local repository
        readFile(fileName);
    }

    public
    void  establishAndCheckJourney( String journey, int xFinal, int yFinal, String dirFinal) {

        boolean returnVal;
        returnVal = MarsRover.establishJourney(journey);

        System.out.println("Journey Okay: " + returnVal);
        System.out.println();
if (false) {
    int theHeading = -1;

    if (returnVal == false)
        throw new AssertionError("establishAndCheckInitialState failed");
    else {
        switch (dirFinal) {
            case "N":
                theHeading = 0;
                break;
            case "S":
                theHeading = 180;
                break;
            case "E":
                theHeading = 90;
                break;
            case "W":
                theHeading = 270;
                break;
        }

        int headingError = theHeading - MarsRover.currentState.heading;

        assertAll(
                () -> assertEquals(xFinal, MarsRover.currentState.posX),
                () -> assertEquals(yFinal, MarsRover.currentState.posY),
                () -> assertEquals(headingError, 0)
        );
    }
}
    }

    @Test
    public void journeyTest() {
        String fileName = "checkjourney.txt";  // in the local repository
        readFile(fileName);
    }
}
