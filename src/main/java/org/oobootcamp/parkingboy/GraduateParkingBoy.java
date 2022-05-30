package org.oobootcamp.parkingboy;


import org.oobootcamp.exception.NoAvailableParkingLotException;
import org.oobootcamp.exception.NoMatchedParkingLotException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;

import java.util.List;
import java.util.Optional;

public class GraduateParkingBoy  extends ParkingBoy{

    public GraduateParkingBoy(List<ParkingLot> managedParkingLots) {
       super(managedParkingLots);
    }

    public Optional<ParkingLot> findAvailableParkingLot() {
        return managedParkingLots.stream()
                                 .filter(ParkingLot::hasSpace)
                                 .findFirst();
    }


}
