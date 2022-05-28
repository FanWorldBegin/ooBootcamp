package org.oobootcamp.parkingboy;

import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parkingboy.strategy.FirstFreeStrategy;

import java.util.Optional;
import java.util.Set;

public class ParkingManager extends ParkingBoy {

    private final Set<ParkingBoy> managedParkingBoys;

    public ParkingManager(Set<ParkingBoy> managedParkingBoys, ParkingLot... managedParkingLots) {
        super(new FirstFreeStrategy(), managedParkingLots);
        this.managedParkingBoys = managedParkingBoys;
    }

    public Optional<ParkingBoy> findParkingBoy() {
        managedParkingBoys.add(this);
        return managedParkingBoys.stream()
                                 .filter(parkingBoy -> parkingBoy.findAvailableParkingLot().isPresent())
                                 .findAny();
    }
}
