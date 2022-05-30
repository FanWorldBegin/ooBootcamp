package org.oobootcamp.parkingboy;

import org.oobootcamp.exception.NotFoundParkingBoyException;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;
import org.oobootcamp.parkingboy.strategy.FirstFreeStrategy;

import java.util.Optional;
import java.util.Set;

public class ParkingManager extends ParkingBoy {

    private final Set<ParkingBoy> managedParkingBoys;

    public ParkingManager(Set<ParkingBoy> managedParkingBoys, ParkingLot... managedParkingLots) {
        super(new FirstFreeStrategy(), managedParkingLots);
        this.managedParkingBoys = managedParkingBoys;
    }

    public ParkingBoy findParkingBoy() {
        managedParkingBoys.add(this);
        return managedParkingBoys.stream()
                                 .filter(parkingBoy -> parkingBoy.findAvailableParkingLot().isPresent())
                                 .findAny()
                                 .orElseThrow(NotFoundParkingBoyException::new);
    }

  public ParkingBoy findParkingBoyByTicket(Ticket ticket) {
      managedParkingBoys.add(this);
      return managedParkingBoys.stream()
                               .filter(parkingBoy -> parkingBoy.findParkingLotByTicket(ticket) != null)
                               .findAny()
                               .orElseThrow(NotFoundParkingBoyException::new);
  }
}
