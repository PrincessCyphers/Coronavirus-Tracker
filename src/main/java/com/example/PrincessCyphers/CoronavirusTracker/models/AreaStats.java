package com.example.PrincessCyphers.CoronavirusTracker.models;

public class AreaStats {
    private String state;
    private String country;
    private int latestNumberOfCases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestNumberOfCases() {
        return latestNumberOfCases;
    }

    public void setLatestNumberOfCases(int latestNumberOfCases) {
        this.latestNumberOfCases = latestNumberOfCases;
    }

    @Override
    public String toString() {
        return "AreaStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestNumberOfCases=" + latestNumberOfCases +
                '}';
    }
}
