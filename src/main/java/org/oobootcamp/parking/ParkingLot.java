package org.oobootcamp.parking;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private int remainingCapacity;
    private final List<ParkingRecord> parkingRecords;

    public ParkingLot(int capacity) {
        if (capacity < 1) {
            throw new InvalidParameterException("capacity must bigger than 0.");
        }
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.parkingRecords = new ArrayList<>();
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

    public boolean carIn(Car car) {
        if (remainingCapacity < 1) {
            return false;
        }
        this.addRecord(new ParkingRecord(car.platNumber(), LocalDateTime.now()));
        this.remainingCapacity -= 1;

        return true;
    }

    private void addRecord(ParkingRecord parkingRecord) {
        this.parkingRecords.add(parkingRecord);
    }

    public List<ParkingRecord> getParkingRecords() {
        return this.parkingRecords;
    }

    public boolean carOut(Car car) {
        var records = this.parkingRecords.stream().filter(parkingRecord -> parkingRecord.getPlatNumber().equals(car.platNumber()) && parkingRecord.getEntryTime() != null).toList();
        if (records.size() == 1) {
            records.get(0).setLeaveTime(LocalDateTime.now());
            this.remainingCapacity += 1;
            return true;
        }

        return false;
    }
}
