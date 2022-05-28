package org.oobootcamp.parking;

import java.time.LocalDateTime;

public record Ticket (String plateNumber, LocalDateTime entryTime, int parkingLotNumber){
}
