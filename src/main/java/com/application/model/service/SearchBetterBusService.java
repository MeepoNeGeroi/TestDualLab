package com.application.model.service;

import com.application.model.StaticFields;
import com.application.model.entity.BusSchedule;

import java.util.List;
import java.util.Scanner;

public enum SearchBetterBusService {
    INSTANCE;

    public String execute(Scanner sc){
        fillSchedules(sc);
        findBetterBus();
        StaticFields.poshes = checkArray(StaticFields.poshes);
        StaticFields.grottys = checkArray(StaticFields.grottys);
        return formAnswer();
    }

    private List<BusSchedule> clearNull(List<BusSchedule> busSchedules){
        while(busSchedules.contains(null)){
            busSchedules.remove(busSchedules.indexOf(null));
        }

        return busSchedules;
    }

    private List<BusSchedule> checkArray(List<BusSchedule> busSchedules){
        int compareIndex;
        busSchedules = clearNull(busSchedules);

        for(int i = 0; i < busSchedules.size(); i++){
            for(int j = i; j < busSchedules.size(); j++){
                compareIndex = compare(busSchedules.get(i), busSchedules.get(j));
                if(compareIndex == 1){
                    busSchedules.set(j, null);
                }
                if(compareIndex == 2){
                    busSchedules.set(i, null);
                }
            }
        }

        busSchedules = clearNull(busSchedules);

        return busSchedules;
    }

    private void fillSchedules(Scanner sc){
        String[] line;
        while(sc.hasNext()){
            line = sc.nextLine().split(" ");
            if(line[0].equals("Posh")){
                StaticFields.poshes.add(
                        new BusSchedule(line[1], line[2])
                );
            }
            else{
                StaticFields.grottys.add(
                        new BusSchedule(line[1], line[2])
                );
            }
        }
    }

    private void findBetterBus(){
//        List<BusSchedule> mainBusSchedules;
//        List<BusSchedule> busSchedules;
//
//        if(StaticFields.grottys.size() > StaticFields.poshes.size()){
//            mainBusSchedules = StaticFields.grottys;
//            busSchedules = StaticFields.poshes;
//        }
//        else{
//            mainBusSchedules = StaticFields.poshes;
//            busSchedules = StaticFields.grottys;
//        }

        for (int i = 0; i < StaticFields.poshes.size(); i++) {
            for (int j = 0; j < StaticFields.grottys.size(); j++) {
                compareBuses(i, j);
            }
        }
    }

    private void compareBuses(int i, int j){
        int compareIndex = compare(StaticFields.poshes.get(i), StaticFields.grottys.get(j));
        if(compareIndex == 0 || compareIndex == 1){
            StaticFields.grottys.remove(j);
        }
        if(compareIndex == 2){
            StaticFields.poshes.set(i, null);
        }
    }

    private int compare(BusSchedule firstBusSchedule, BusSchedule secondBusSchedule){
        if(firstBusSchedule.equals(secondBusSchedule)){
            return 0;
        }
        if(firstBusSchedule.getDepartureTime().equals(secondBusSchedule.getDepartureTime())){
            if(firstBusSchedule.getArrivalTime().before(secondBusSchedule.getArrivalTime())){
                return 1;
            }
            else{
                return 2;
            }
        }
        if(firstBusSchedule.getArrivalTime().equals(secondBusSchedule.getArrivalTime())){
            if(firstBusSchedule.getDepartureTime().before(secondBusSchedule.getDepartureTime())){
                return 2;
            }
            else{
                return 1;
            }
        }
        if(firstBusSchedule.getDepartureTime().after(secondBusSchedule.getDepartureTime()) &&
                firstBusSchedule.getArrivalTime().before(secondBusSchedule.getArrivalTime())){
            return 1;
        }
        if(secondBusSchedule.getDepartureTime().after(firstBusSchedule.getDepartureTime()) &&
                secondBusSchedule.getArrivalTime().before(firstBusSchedule.getArrivalTime())){
            return 1;
        }
        return -1;
    }

    private String formAnswer(){
        String answer = "";

        for (BusSchedule b:
             StaticFields.poshes) {
            if(b.getDepartureTime() != null) {
                answer += "Posh " + b + "\n";
            }
        }

        answer += "\n";

        for(int i = 0; i < StaticFields.grottys.size() - 1; i++){
            answer += "Grotty " + StaticFields.grottys.get(i) + "\n";
        }
        answer += "Grotty " + StaticFields.grottys.get(StaticFields.grottys.size() - 1);

        return answer;
    }
}
