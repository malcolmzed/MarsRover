package MarsRover;

import MarsRover.UserInput.UserInput;
import MarsRover.MarsGraphics.MarsGraphics;
import MarsRover.Travel.Travel;

import java.awt.*;

public
class MarsRover {
    public static
    void main(String[] args) {

        boolean ok;
        ok = UserInput.getAndSetPlateau();
        ok = UserInput.getAndSetInitialState();
        ok = UserInput.getAndSetJourney();

        Graphics theGraphics = MarsGraphics.prepareWindow();

        Travel.doTheJourney(theGraphics);
    }
}
