package MarsRover.Journey;

public
class Journey {
    private static String theJourney;
    private static int currentIndex = -1;

    public static void setJourney(String journey) {
        theJourney = journey;
    }

    public static
    String getTheJourney() {
        return theJourney;
    }

    public static char getNextJourneyChar() {
        currentIndex++;
        if (currentIndex < theJourney.length())
            return theJourney.charAt(currentIndex);
        else
            return 'x';
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }
}
