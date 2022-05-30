package org.oobootcamp.parkingboy.strategy;

import org.oobootcamp.parking.ParkingLot;

import java.util.List;
import java.util.Optional;

public class FirstFreeStrategy implements FindParkingLotStrategy {
    @Override
    public Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLotLists) {
        return parkingLotLists.stream()
                              .filter(ParkingLot::hasSpace)
                              .findFirst();
    }
}
