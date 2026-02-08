package com.example.ca1bookingfx;

import java.util.LinkedList;

public class Show {
    private String title;
    private int runningTime; // in minutes
    private String startDate;
    private String endDate;
    private int stallPrice;
    private int circlePrice;
    private int balconyPrice;
    private java.util.LinkedList<Performance> performances = new java.util.LinkedList<>();

    public Show(String title, int runningTime, String startDate, String endDate,
                int stallPrice, int circlePrice, int balconyPrice){

        this.title = title;
        this.runningTime = runningTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stallPrice = stallPrice;
        this.circlePrice = circlePrice;
        this.balconyPrice = balconyPrice;
    }

    // Getter for performances
    public LinkedList<Performance> getPerformances() {
        return performances; // This should return the list of performances for the show
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getRunningTime() {
        return runningTime;
    }
    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public int getStallPrice() {
        return stallPrice;
    }
    public void setStallPrice(int stallPrice) {
        this.stallPrice = stallPrice;
    }
    public int getCirclePrice() {
        return circlePrice;
    }
    public void setCirclePrice(int circlePrice) {
        this.circlePrice = circlePrice;
    }
    public int getBalconyPrice() {
        return balconyPrice;
    }
    public void setBalconyPrice(int balconyPrice) {
        this.balconyPrice = balconyPrice;
    }

    @Override
    public String toString() {
        return "Show{" +
                "title='" + title + '\'' +
                ", runningTime=" + runningTime +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", stallPrice=" + stallPrice +
                ", circlePrice=" + circlePrice +
                ", balconyPrice=" + balconyPrice +
                '}';
    }

    public void addPerformance(Performance performance) {
    }
    public void removePerformance(Performance performance) {
        performances.remove(performance);
    }

}