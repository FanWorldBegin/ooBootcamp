package org.oobootcamp.parkingboy.strategy;

import org.oobootcamp.parking.ParkingLot;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface FindParkingLotStrategy {

    Optional<ParkingLot> findAvailableParkingLot(List<ParkingLot> parkingLotLists);
}
