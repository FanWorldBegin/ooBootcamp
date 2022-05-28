package org.oobootcamp.parkingboy.strategy;

import org.oobootcamp.parking.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FirstMaxSpaceStrategy implements FindParkingLotStrategy {
    @Override
    public Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLotLists) {
        return parkingLotLists.stream()
                              .filter(ParkingLot::hasSpace)
                              .max(Comparator.comparingInt(ParkingLot::getRemainingCapacity));
    }
}
