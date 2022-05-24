package org.oobootcamp.core.parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Parkinglot {
    public static int totalCartports = 0;
    public static Map<String, String> parkingMap;

    public void setTotalCarports(int parkingNumber) {
        this.totalCartports =  parkingNumber;
    }

    public int getTotalCarpots() {
        return totalCartports;
    }

    public String generateTicket(String carNumbers, LocalDateTime timestamp) {

        String timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(timestamp);

        return timeFormat + "-" + carNumbers;
    }

    public boolean hasEmptySpot() {
        if(parkingMap.size() <  totalCartports) {
            return true;
        }

        return false;
    }
}
