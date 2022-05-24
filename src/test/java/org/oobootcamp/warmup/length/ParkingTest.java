package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oobootcamp.core.parkinglot.Parkinglot;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;


public class ParkingTest {
    Parkinglot parkinglot;

    @BeforeEach
    public void setUp() {
        parkinglot = new Parkinglot();
    }
    @AfterEach
    public void tearDown() {
        Parkinglot.parkingMap = null;
    }
    @Test
    void should__total_carports_equal_to_setting_number() {
//        given
        Integer parkingNumber = 10;
//        when
        parkinglot.setTotalCarports(parkingNumber);
//        then
        Assertions.assertEquals(parkingNumber, parkinglot.getTotalCarpots());
    }

    @Test
    void should_return_ticket_when_parking_a_car() throws ParseException {
        String  carNumbers = "A88888";
        LocalDateTime ldt = LocalDateTime.of(2020, 8, 1, 3,8,8);
        String ticket = "2020-08-01 03:08:08-A88888";
        String returnedTicket = parkinglot.generateTicket(carNumbers,ldt);
        Assertions.assertEquals(ticket, returnedTicket);
    }

    @Test
    void should_return_true_when_there_is_empty_spot() {
        //        given
        Integer parkingNumber = 3;
        parkinglot.setTotalCarports(parkingNumber);
        HashMap<String, String> carListMap = new HashMap<String, String>();
        carListMap.put("2020-08-01 03:08:08-A88888", "A88888");
        Parkinglot.parkingMap = carListMap;

        Assertions.assertEquals(true, parkinglot.hasEmptySpot() );
    }

    @Test
    void should_return_false_when_there_is_no_empty_spot() {
        //        given
        Integer parkingNumber = 3;
        parkinglot.setTotalCarports(parkingNumber);
        HashMap<String, String> carListMap = new HashMap<String, String>();
        carListMap.put("2020-08-01 03:08:08-A88888", "A88888");
        carListMap.put("2020-08-01 03:08:09-A88887", "A88887");
        carListMap.put("2020-08-01 03:08:10-A88889", "A88889");
        Parkinglot.parkingMap = carListMap;

        Assertions.assertEquals(false, parkinglot.hasEmptySpot() );
    }

    @Test
    void should_park_successfully_when_there_is_empty_spot() {
        //        given
        Integer parkingNumber = 3;
        parkinglot.setTotalCarports(parkingNumber);
        HashMap<String, String> carListMap = new HashMap<String, String>();
        carListMap.put("2020-08-01 03:08:08-A88888", "A88888");
        Parkinglot.parkingMap = carListMap;
    }

    @Test
    void should_allow_parking_and_return_ticket_when_the_carport_bigger_than_zero() {
//        given
        Integer parkingNumber = 3;
        Parkinglot parkinglot = new Parkinglot();
        parkinglot.setTotalCarports(parkingNumber);
        HashMap<String, String> parkingList = new HashMap<String, String>();

    }

    @Test
    void should_forbid_parking_when_the_carport_is_zero() {

    }

}
