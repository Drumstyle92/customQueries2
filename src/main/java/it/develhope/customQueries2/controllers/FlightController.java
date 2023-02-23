package it.develhope.customQueries2.controllers;

import it.develhope.customQueries2.entities.FlightEntity;
import it.develhope.customQueries2.entities.FlightStatus;
import it.develhope.customQueries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Drumstyle92
 * Controller class to handle CRUD of API requests and queries for flight database data
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

    /**
     * Automated instance variable to take useful methods for CRUD
     */
    @Autowired
    private FlightRepository flightRepository;

    /**
     * List of flights where we will apply the CRUD
     */
    List<FlightEntity> newFlights = new ArrayList<>();

    /**
     * @return Returns a string of ten characters
     * Method that generates random strings
     */
    public String generateRandomString(){

        int leftLimit = 97; // letter 'a'

        int rightLimit = 122; // letter 'z'

        int targetStringLength = 10;

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    /**
     * @return Returns a random enumeration about the status of a flight
     * Method that randomly generates a flight status
     */
    public FlightStatus getRandomStatus(){

        return FlightStatus.values()[new Random().nextInt(FlightStatus.values().length)];

    }

    /**
     * @param n Url parameter which, if filled, will represent
     * the number of flights in the list, if it is null, it will be 100.
     * Post method that creates a list of flights
     */
    @PostMapping
    public void provisionFlights(@RequestParam(required = false) Integer n){

         if(n == null) {
            n=100;
        }

        for(int i = 0; i < n; i++){

            FlightEntity flight = new FlightEntity();

            flight.setDescription(generateRandomString());
            flight.setFromAirport(generateRandomString());
            flight.setToAirport(generateRandomString());
            flight.setStatus(getRandomStatus());
            newFlights.add(flight);

        }

        flightRepository.saveAll(newFlights);

    }

    /**
     * @param page Parameter where to indicate the page number
     * @param size Parameter where to indicate the length of the page
     * @return Returns the list with the page and the length inserted in the url
     * Get method to be able to view based on the number and length of
     * the page that we enter the list of flights with the reference order of the from_airport column in ascending
     */
    @GetMapping
    public Page<FlightEntity> getAllFlights(@RequestParam int page, @RequestParam int size){
        return flightRepository.findAll(PageRequest.of(page, size, Sort.by("fromAirport").ascending()));
    }

    /**
     * @param status Parameter where to indicate the status taken into consideration
     * @param page   Parameter where to indicate the page number
     * @param size   Parameter where to indicate the length of the page
     * @return Returns a list of flights filtered by the page and the length taken into consideration,
     * moreover only flights with the status applied through the url will return
     * Get method to display all flights based on status, page and selected length
     */
    @GetMapping("/status/{status}")
    public Page<FlightEntity> getAllFlightsByStatus(@PathVariable FlightStatus status, @RequestParam int page, @RequestParam int size){

        return flightRepository.findAllByStatus(status, (PageRequest.of(page, size)));

    }

    /**
     * @return Returns a list with only flights with ONTIME status
     * Get method to display all punctual flights in the list
     */
    @GetMapping("/ontime")
    public List<FlightEntity> getCustomFlight(){

        List<FlightEntity> flightFilter = flightRepository.findAll();

        List<FlightEntity> flightOnTime = flightFilter.stream()
                .filter(flight -> flight.getStatus().equals(FlightStatus.ONTIME))
                .collect(Collectors.toList());

        return flightOnTime;

    }

    /**
     * @return Returns a list with delayed and canceled flights
     * Get method that displays all the delayed or canceled flights in a list thanks to a custom query
     */
    @GetMapping("/customQuery")
    public List<FlightEntity> getFlightStatusP1orP2(){

        return flightRepository.getCustomStatusFlight();

    }

    }

