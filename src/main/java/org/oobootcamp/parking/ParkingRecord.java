package org.oobootcamp.parking;

import java.time.LocalDateTime;

public class ParkingRecord {
    private final String platNumber;
    private final LocalDateTime entryTime;
    private LocalDateTime leaveTime;

    public ParkingRecord(String platNumber, LocalDateTime entryTime) {
        this.platNumber = platNumber;
        this.entryTime = entryTime;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = leaveTime;
    }
}
