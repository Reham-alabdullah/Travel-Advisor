package de.bredex.travel_advisor.domain;

public class TravelSuggestion {

    private String destinationName;

    private String destinationLocation;


    private String activityDescription;

    private String estimatedCost;

    public TravelSuggestion() {
    }

    public TravelSuggestion(String destinationName, String destinationLocation, String activityDescription, String estimatedCost) {
        this.destinationName = destinationName;
        this.destinationLocation = destinationLocation;
        this.activityDescription = activityDescription;
        this.estimatedCost = estimatedCost;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    @Override
    public String toString() {
        return String.format(
                "Destination: %s\n" +
                        "Location: %s\n" +
                        "Description: %s\n" +
                        "Estimated Cost: %s",
                destinationName, destinationLocation, activityDescription, estimatedCost);
    }


}
