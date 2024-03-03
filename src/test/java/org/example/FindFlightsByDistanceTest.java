package org.example;

import org.example.Model.Flight;
import org.example.Repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class FindFlightsByDistanceTest {

    @Autowired
    private FlightRepository flightRepository;

    @BeforeEach
    public void setup() {
        // Clean the repository if needed
        flightRepository.deleteAll();

        // Setup data here
        flightRepository.save(new Flight("AB123", "Boeing 737", 180, 450));
        flightRepository.save(new Flight("CD456", "Airbus A320", 160, 1200));
        flightRepository.save(new Flight("EF789", "Boeing 747", 366, 525));
    }

    @Test
    public void testFindByFlightMileageGreaterThan() {
        List<Flight> highMileageFlights = flightRepository.findByFlightMileageGreaterThan(500);
        assertEquals(2, highMileageFlights.size());
        assertTrue(highMileageFlights.stream().allMatch(flight -> flight.getFlightMileage() > 500));
    }
}