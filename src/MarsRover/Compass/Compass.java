package MarsRover.Compass;

import MarsRover.Rover.Rover;

import java.lang.reflect.Array;

public
class Compass {

    private
    enum CompassEnum {
        North('N', 0, 0, 1),
        South('S', 180, 0, -1),
        East('E', 90, 1, 0),
        West('W', 270, -1, 0);

        private final char dirChar;
        private final int theHeading;
        private final int xVector;
        private final int yVector;

        CompassEnum(char dirChar, int heading, int xVector, int yVector) {
            this.dirChar = dirChar;
            this.theHeading = heading;
            this.xVector = xVector;
            this.yVector = yVector;
        }
    }

        public static
        String getDirection() {
            int currectHeading = Rover.getHeading();
            for (CompassEnum c : CompassEnum.values()) {
                if (c.theHeading == currectHeading) {
                    return c.dirChar + "";
                }
            }
            return "";
        }

        public static
        int[] getVector() {

            int currectHeading = Rover.getHeading();
            for (CompassEnum c : CompassEnum.values()) {
                if (c.theHeading == currectHeading) {
                    int[] vector = {c.xVector, c.yVector};
                    return vector;
                }
            }

            int[] vector = {};
            return vector;
        }
}


