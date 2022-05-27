package org.oobootcamp.parkingboy;


import org.oobootcamp.exception.NoAvailableParkingLotException;
import org.oobootcamp.exception.NoMatchedParkingLotException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy {
    List<ParkingLot> managedParkingLots;

    public GraduateParkingBoy(List<ParkingLot> managedParkingLots) {
        this.managedParkingLots = managedParkingLots;
    }

    private Optional<ParkingLot> findAvailableParkingLot() {
        return managedParkingLots.stream()
                .sorted((o1, o2) -> o2.getParkingLotNumber() - o1.getParkingLotNumber())
                .filter(parkingLot -> parkingLot.getRemainingCapacity() > 0)
                .findFirst();
    }

    Optional<Ticket> parkCar(Car car) throws NoAvailableParkingLotException {
        ParkingLot parkingLot = findAvailableParkingLot().orElseThrow(NoAvailableParkingLotException::new);
        return parkingLot.carIn(car);
    }

    boolean pickCar(Ticket ticket) throws NoMatchedParkingLotException {
        ParkingLot parkingLot = managedParkingLots.stream()
                                                  .filter(n -> n.getParkingLotNumber() == ticket.parkingLotNumber())
                                                  .findAny()
                                                  .orElseThrow(NoMatchedParkingLotException::new);
        return parkingLot.carOut(ticket);
    }
}
