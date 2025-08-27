package de.bredex.travel_advisor.domain;

import java.time.LocalDate;

public class UserPreferences {
    private String preferredDestination;
    private String travelStyle;
    private String budget;
    private Integer durationDays;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean withKids;

    public UserPreferences() {
    }

    public UserPreferences(String favoriteDestination,
                           String travelStyle,
                           String budget,
                           Integer interests,
                           boolean withKids,
                           LocalDate dateFrom,
                           LocalDate dateTo
    ) {
        this.preferredDestination = favoriteDestination;
        this.travelStyle = travelStyle;
        this.budget = budget;
        this.durationDays = interests;
        this.withKids = withKids;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getPreferredDestination() {
        return preferredDestination;
    }

    public void setPreferredDestination(String preferredDestination) {
        this.preferredDestination = preferredDestination;
    }

    public String getTravelStyle() {
        return travelStyle;
    }

    public void setTravelStyle(String travelStyle) {
        this.travelStyle = travelStyle;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public boolean isWithKids() {
        return withKids;
    }

    public void setWithKids(boolean withKids) {
        this.withKids = withKids;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
