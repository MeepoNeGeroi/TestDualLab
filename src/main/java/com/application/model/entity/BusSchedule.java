package com.application.model.entity;

import java.sql.Time;
import java.util.Objects;

public class BusSchedule {
    private Time departureTime;
    private Time arrivalTime;

    public BusSchedule(){}

    public BusSchedule(String departureTime, String arrivalTime) {
        this.departureTime = new Time(Integer.parseInt(departureTime.split(":")[0])
                , Integer.parseInt(departureTime.split(":")[1]), 0);
        this.arrivalTime = new Time(Integer.parseInt(arrivalTime.split(":")[0])
                , Integer.parseInt(arrivalTime.split(":")[1]), 0);
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private String convertTime(Time time){
        String result = "";
        if(time.getHours() < 10){
            result += "0" + time.getHours() + ":";
        }
        else{
            result += time.getHours() + ":";
        }

        if(time.getMinutes() < 10){
            result += "0" + time.getMinutes();
        }
        else{
            result += time.getMinutes();
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusSchedule that = (BusSchedule) o;
        return Objects.equals(departureTime, that.departureTime) && Objects.equals(arrivalTime, that.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureTime, arrivalTime);
    }

    @Override
    public String toString() {
        return convertTime(departureTime) + " " + convertTime(arrivalTime);
    }
}