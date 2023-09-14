package MarsRover.location;

public enum Heading {

    N('N', 0, new int[]{0,1}),
    E('E',90, new int[]{1,0}),
    S('S',180, new int[]{0,-1}),
    W('W',270, new int[]{-1,0});


    public final char character;
    public final int degrees;
    public final int[] vector;

    Heading(char character, int degrees, int[] vector) {
        this.character = character;
        this.degrees = degrees;
        this.vector = vector;
    }

}
