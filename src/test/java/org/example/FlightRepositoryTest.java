package org.example;

import org.example.Model.Flight;
import org.example.Repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void testCreateNewFlight() {
        // Given
        String flightNumber = "FN123";
        String aircraft = "Boeing 747";
        int totalAircraftSeats = 200;
        int flightMileage = 5000;

        // When
        Flight flight = new Flight(flightNumber, aircraft, totalAircraftSeats, flightMileage);
        flight = flightRepository.save(flight);

        // Then
        Flight savedFlight = flightRepository.findById(flight.getFlightId()).orElse(null);
        assertThat(savedFlight).isNotNull();
        assertThat(savedFlight.getFlightNumber()).isEqualTo(flightNumber);
        assertThat(savedFlight.getAircraft()).isEqualTo(aircraft);
        assertThat(savedFlight.getTotalAircraftSeats()).isEqualTo(totalAircraftSeats);
        assertThat(savedFlight.getFlightMileage()).isEqualTo(flightMileage);
    }
}
