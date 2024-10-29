package exception;

public class TemperatureExceededException extends Exception {
    private Integer kitNumber;
    private String neighborhood;

    public TemperatureExceededException(String message, Integer kitNumber, String neighborhood) {
        super(message);
        this.kitNumber = kitNumber;
        this.neighborhood = neighborhood;
    }

    public int getKitNumber() {
        return kitNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }
}

