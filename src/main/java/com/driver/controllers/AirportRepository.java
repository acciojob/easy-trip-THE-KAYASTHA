package com.driver.controllers;


import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepository {

    HashMap<String,Airport> airportHashMap=new HashMap<>();
    HashMap<Integer, Flight> flightHashMap=new HashMap<>();

    HashMap<Integer,Integer> fareHashMap=new HashMap<>();
    HashMap<Integer,Passenger> passengerHashMap=new HashMap<>();
    HashMap<Integer, List<Integer>> bookingHashMap=new HashMap<>();
    public void addAirport(Airport airport) {

        airportHashMap.put(airport.getAirportName(),airport);
    }

    public HashMap<String, Airport> getLargestAirportName() {

        return airportHashMap;
    }

    public HashMap<Integer, Flight> getShortestDurationOfPossibleBetweenTwoCities() {

        return flightHashMap;
    }

    public  int getNumberOfPeopleOn(Date date, String airportName) {

        int count=0;

        for(Integer i: flightHashMap.keySet()){
            Flight temp=flightHashMap.get(i);

            if(temp.getFlightDate()==date &&(temp.getToCity()==airportHashMap.get(airportName).getCity()||temp.getFromCity()==airportHashMap.get(airportName).getCity())){
                count++;
            }



        }
        return count;

    }

    public String bookATicket(Integer flightId, Integer passengerId) {

        if(bookingHashMap.containsKey(flightId)){
            if(flightHashMap.get(flightId).getMaxCapacity()<=bookingHashMap.get(flightId).size()||bookingHashMap.get(flightId).contains(passengerId)){
                return "FAILURE";
            }

            else{
                int fare=calculateFlightFare(flightId);
                fareHashMap.put(flightId,fareHashMap.getOrDefault(flightId,0)+fare);
                bookingHashMap.get(flightId).add(passengerId);
                return "SUCCESS";
            }
        }
        else {
            fareHashMap.put(flightId,3000);
            List<Integer> temp = new ArrayList<>();
            temp.add(passengerId);
            bookingHashMap.put(flightId, temp);
            return "SUCCESS";
        }
    }

    public int calculateFlightFare(Integer flightId) {
            int fare=3000+(bookingHashMap.get(flightId).size())*50;



        return fare;
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {


        if(bookingHashMap.containsKey(flightId)){
            if(bookingHashMap.get(flightId).contains(passengerId)){
                List<Integer> ll=bookingHashMap.get(flightId);
                for(int i=0;i< ll.size();i++){
                    if(ll.get(i)==passengerId){
                        fareHashMap.put(flightId,fareHashMap.get(flightId)-(3000+i*50));
                    }
                }
                bookingHashMap.get(flightId).remove(passengerId);
                return "SUCCESS";
            }
            else{
                return "FAILURE";
            }
        }
        else{
            return "FAILURE";
        }
    }

    public HashMap<Integer, List<Integer>> countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return bookingHashMap;
    }

    public void addFlight(Flight flight) {

        flightHashMap.put(flight.getFlightId(),flight);
    }

    public String getAirportNameFromFlightId(Integer flightId) {

        if(!flightHashMap.containsKey(flightId)){
            return null;
        }
        City temp=flightHashMap.get(flightId).getFromCity();

        for(String i: airportHashMap.keySet()){

            if(airportHashMap.get(i).getCity()==temp){
                return i;
            }
        }
        return null;
    }

    public void addPassenger(Passenger passenger) {
            passengerHashMap.put(passenger.getPassengerId(), passenger);


    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        return fareHashMap.get(flightId);

    }
}
