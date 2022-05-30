package org.oobootcamp.parking;

import org.oobootcamp.exception.CarNotFoundException;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ParkingLot {
    private int capacity;
    private int remainingCapacity;
    private final Map<Ticket, Car> parkedCars;
    private final String parkingLotNumber;

    public ParkingLot(int capacity) {
        if (capacity < 1) {
            throw new InvalidParameterException("capacity must bigger than 0.");
        }
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.parkedCars = new HashMap<>();
        parkingLotNumber = UUID.randomUUID().toString();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < this.capacity - remainingCapacity) {
            throw new InvalidParameterException("capacity must not less than parked cars number, current parked cars number: %s.".formatted(this.capacity - remainingCapacity));
        }
        this.remainingCapacity = this.remainingCapacity + (capacity - this.capacity);
        this.capacity = capacity;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

    public Optional<Ticket> carIn(Car car) {
        if (remainingCapacity < 1) {
            return Optional.empty();
        }
        Ticket ticket = new Ticket(car.plateNumber(), LocalDateTime.now(), parkingLotNumber);
        this.parkedCars.put(ticket, car);
        this.remainingCapacity -= 1;

        return Optional.of(ticket);
    }

    public Car carOut(Ticket ticket) {
        // 当进入时间存在 且离开时间为null的时候才可以离开 - 历史记录已经离开了
        if (parkedCars.containsKey(ticket)) {
            return parkedCars.remove(ticket);
        }
        throw new CarNotFoundException();
    }

    public String getParkingLotNumber() {
        return parkingLotNumber;
    }

    public boolean hasSpace() {
        return remainingCapacity > 0;
    }
}
