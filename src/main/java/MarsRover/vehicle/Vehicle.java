package MarsRover.vehicle;

import MarsRover.location.Heading;

public abstract class Vehicle {

    private int x;
    private int y;

    private int degrees;
    private Heading heading;

    public Vehicle(int x, int y, Heading heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.setDegrees(heading);
    }


    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    public void setDegrees(Heading heading) {
        this.degrees = heading.degrees;
    }
}


