package org.oobootcamp.parkingboy;

import org.oobootcamp.exception.NoAvailableParkingLotException;
import org.oobootcamp.exception.NoMatchedParkingLotException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;

import java.util.List;
import java.util.Optional;

public abstract class ParkingBoy {
  List<ParkingLot> managedParkingLots;

  public ParkingBoy(List<ParkingLot> parkingLot) {
    this.managedParkingLots = parkingLot;
  }

  protected abstract Optional<ParkingLot> findAvailableParkingLot();

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
