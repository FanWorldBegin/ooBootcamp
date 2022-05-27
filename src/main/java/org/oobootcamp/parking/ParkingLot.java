package org.oobootcamp.parking;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {
    private int capacity;
    private int remainingCapacity;
    private final List<ParkingRecord> parkingRecords;
    private int parkingLotNumber;

    public ParkingLot(int capacity) {
        if (capacity < 1) {
            throw new InvalidParameterException("capacity must bigger than 0.");
        }
        this.capacity = capacity;
        this.remainingCapacity = capacity;
        this.parkingRecords = new ArrayList<>();
        parkingLotNumber = (int) System.currentTimeMillis();
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
        LocalDateTime entryTime = LocalDateTime.now();
        this.addRecord(new ParkingRecord(car.platNumber(), entryTime));
        this.remainingCapacity -= 1;

        return Optional.of(new Ticket(car.platNumber(), entryTime, parkingLotNumber));
    }

    private void addRecord(ParkingRecord parkingRecord) {
        this.parkingRecords.add(parkingRecord);
    }

    public List<ParkingRecord> getParkingRecords() {
        return this.parkingRecords;
    }

    public boolean carOut(Ticket ticket) {
        // 当进入时间存在 且离开时间为null的时候才可以离开 - 历史记录已经离开了
        var records = this.parkingRecords.stream().filter(parkingRecord -> parkingRecord.getPlatNumber().equals(ticket.platNumber()) && parkingRecord.getEntryTime() != null && parkingRecord.getLeaveTime() == null).toList();
        if (records.size() == 1) {
            records.get(0).setLeaveTime(LocalDateTime.now());
            this.remainingCapacity += 1;
            return true;
        }

        return false;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }
}
