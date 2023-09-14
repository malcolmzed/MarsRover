package MarsRover.plateau;

import MarsRover.vehicle.Rover;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    private final int xMax;
    private final int yMax;

    private final List<Rover> rovers;

    public Plateau(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.rovers = new ArrayList<>();
    }

    public boolean addRover(Rover rover) {
        this.rovers.add(rover);
        return true;
    }

}
