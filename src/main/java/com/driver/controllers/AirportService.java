package com.driver.controllers;


import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AirportService {

    AirportRepository repoObj=new AirportRepository();
    public void addAirport(Airport airport) {

        repoObj.addAirport(airport);
    }

    public String getLargestAirportName() {

        HashMap<String,Airport> hh=repoObj.getLargestAirportName();
        List<String> ans=new ArrayList<>();
            int maxTerminal=Integer.MIN_VALUE;

            for(String i: hh.keySet()){

                if(hh.get(i).getNoOfTerminals()>maxTerminal){
                    maxTerminal=hh.get(i).getNoOfTerminals();
                }

            }

            for(String i: hh.keySet()){
                if(hh.get(i).getNoOfTerminals()==maxTerminal){
                    ans.add(i);
                }
            }
            if(ans.size()==1){
                return ans.get(0);
            }
            else{

                Collections.sort(ans);
                return ans.get(0);
            }
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {

        HashMap<Integer, Flight> temp=repoObj.getShortestDurationOfPossibleBetweenTwoCities();

        boolean flightAvailable=false;
        double shortestDis=Double.MAX_VALUE;
        for(Integer i: temp.keySet()){
            Flight ff=temp.get(i);

            if(ff.getFromCity()==fromCity&&ff.getToCity()==toCity){
                flightAvailable=true;
                if(shortestDis>ff.getDuration()){
                    shortestDis=ff.getDuration();
                }

            }


        }
        if(flightAvailable){
            return shortestDis;
        }
        else{
            return -1.0;
        }
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int ans=repoObj.getNumberOfPeopleOn(date,airportName);
        return ans;



    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        String ans=repoObj.bookATicket(flightId,passengerId);
        return ans;
    }

    public int calculateFlightFare(Integer flightId) {
        int ans=repoObj.calculateFlightFare(flightId);
        return ans;

    }

    public String cancelATicket(Integer flightId, Integer passengerId) {

        String ans=repoObj.cancelATicket(flightId,passengerId);
        return ans;
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        HashMap<Integer,List<Integer>> hh=repoObj.countOfBookingsDoneByPassengerAllCombined(passengerId);

        int count=0;

        for(Integer i:hh.keySet()){
            if(hh.get(i).contains(passengerId)){
                count++;
            }

        }
        return count;

    }

    public void addFlight(Flight flight) {

        repoObj.addFlight(flight);
    }

    public String getAirportNameFromFlightId(Integer flightId) {

        String ans=repoObj.getAirportNameFromFlightId(flightId);
        return ans;
    }

    public void addPassenger(Passenger passenger) {
        repoObj.addPassenger(passenger);
    }

    public int calculateRevenueOfAFlight(Integer flightId) {

        int ans=repoObj.calculateRevenueOfAFlight(flightId);
        return ans;

    }
}
