package org.oobootcamp.parkingboy;

import org.oobootcamp.exception.NoAvailableParkingLotException;
import org.oobootcamp.exception.NoMatchedParkingLotException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;
import org.oobootcamp.parkingboy.strategy.FindParkingLotStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {

    private final List<ParkingLot> managedParkingLots;

    private final FindParkingLotStrategy strategy;

    public ParkingBoy(FindParkingLotStrategy strategy, ParkingLot... managedParkingLots) {
        this.strategy = strategy;
        this.managedParkingLots = Arrays.asList(managedParkingLots);
    }

    public Optional<ParkingLot> findAvailableParkingLot() {
        return strategy.findAvailableParkingLot(managedParkingLots);
    }

    Optional<Ticket> parkCar(Car car) throws NoAvailableParkingLotException {
        ParkingLot parkingLot = findAvailableParkingLot().orElseThrow(NoAvailableParkingLotException::new);
        return parkingLot.carIn(car);
    }

    Car pickCar(Ticket ticket) throws NoMatchedParkingLotException {
        ParkingLot parkingLot = managedParkingLots.stream()
                                                  .filter(n -> n.getParkingLotNumber() == ticket.parkingLotNumber())
                                                  .findAny()
                                                  .orElseThrow(NoMatchedParkingLotException::new);
        return parkingLot.carOut(ticket);
    }
}
