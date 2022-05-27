package org.oobootcamp.parking;

import java.time.LocalDateTime;

public record Ticket (String platNumber, LocalDateTime entryTime, int parkingLotNumber){
}
