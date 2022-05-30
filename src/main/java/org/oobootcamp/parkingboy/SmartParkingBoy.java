package org.oobootcamp.parkingboy;

import org.oobootcamp.parking.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {
  public SmartParkingBoy(List<ParkingLot> parkingLot) {
    super(parkingLot);
  }

  @Override
  protected Optional<ParkingLot> findAvailableParkingLot() {
    return managedParkingLots.stream()
      .filter(ParkingLot::hasSpace)
      .max(Comparator.comparingInt(ParkingLot::getRemainingCapacity));
  }
}
