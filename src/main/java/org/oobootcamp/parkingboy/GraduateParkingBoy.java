package org.oobootcamp.parkingboy;


import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;

import java.util.Comparator;
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

    Optional<Ticket> parkCar(Car car) throws Exception {
        ParkingLot parkingLot = findAvailableParkingLot().orElseThrow(Exception::new);
        return parkingLot.carIn(car);
    }

    boolean pickCar(Ticket ticket) throws Exception {
        ParkingLot parkingLot = managedParkingLots.stream()
                                                  .filter(n -> n.getParkingLotNumber() == ticket.parkingLotNumber())
                                                  .findAny()
                                                  .orElseThrow(Exception::new);
        return parkingLot.carOut(ticket);
    }
}
