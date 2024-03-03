package org.example;

import org.example.Model.Flight;
import org.example.Repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FindAircraftByContainingNameTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testFindAircraftByContainingName() {
        // Given
        String aircraftNameToFind = "Boeing 747";
        Flight flight1 = new Flight("ABC123", aircraftNameToFind, 150, 1000);
        Flight flight2 = new Flight("XYZ789", "Airbus A320", 120, 800);

        flightRepository.save(flight1);
        flightRepository.save(flight2);

        // When
        List<Flight> foundFlights = flightRepository.findByAircraftContaining(aircraftNameToFind);

        // Then
        assertThat(foundFlights).isNotEmpty();
        assertThat(foundFlights).hasSize(1);
        assertThat(foundFlights.get(0).getAircraft()).containsIgnoringCase(aircraftNameToFind);
    }
}

