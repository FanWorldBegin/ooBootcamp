package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;


class ParkingLotTest {

    @Test
    void should_set_capacity_and_remaining_capacity_success_when_int_parking_lot_given_capacity_bigger_than_zero() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertEquals(1, parkingLot.getCapacity());
        assertEquals(1, parkingLot.getRemainingCapacity());
    }

    @Test
    void should_set_capacity_and_remaining_capacity_fail_when_int_parking_lot_given_capacity_less_than_one() {
        int capacity = 0;

        Executable executable = () -> new ParkingLot(capacity);

        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class, executable);
        Assertions.assertEquals("capacity must bigger than 0.", invalidParameterException.getMessage());
    }

    @Test
    void should_allow_in_and_create_parking_record_and_minus_remaining_capacity_when_remaining_capacity_bigger_than_zero_given_car_wanna_in() {
        var parkingLot = new ParkingLot(10);
        var car = new Car("鄂A 88888");

        var allowIn = parkingLot.carIn(car);

        assertTrue(allowIn);
        assertEquals(1, parkingLot.getParkingRecords().size());
        assertNotNull(parkingLot.getParkingRecords().get(0).getEntryTime());
        assertEquals(car.platNumber(), parkingLot.getParkingRecords().get(0).getPlatNumber());
        assertEquals(9, parkingLot.getRemainingCapacity());
    }

    @Test
    void should_forbid_in_when_remaining_capacity_less_than_one_given_car_wanna_in() {
        var parkingLot = new ParkingLot(1);
        var car1 = new Car("鄂A 88888");
        var car2 = new Car("鄂A 66666");
        parkingLot.carIn(car1);

        var allowIn = parkingLot.carIn(car2);
        assertFalse(allowIn);
    }

    @Test
    void should_allow_out_and_update_parking_record_and_remaining_capacity_plus_one_when_parking_in_record_exist_given_car_wanna_out() {
        var parkingLot = new ParkingLot(10);
        var car1 = new Car("鄂A 88888");
        var car2 = new Car("鄂A 66666");
        parkingLot.carIn(car1);
        parkingLot.carIn(car2);

        var allowOut = parkingLot.carOut(car1);

        assertTrue(allowOut);
        assertEquals(2, parkingLot.getParkingRecords().size());
        assertNotNull(parkingLot.getParkingRecords().get(0).getLeaveTime());
        assertEquals(car1.platNumber(), parkingLot.getParkingRecords().get(0).getPlatNumber());
        assertEquals(9, parkingLot.getRemainingCapacity());
    }

    @Test
    void should_forbid_out_when_no_record__exist_given_car_wanna_out() {
        var parkingLot = new ParkingLot(10);
        var car1 = new Car("鄂A 88888");
        var car2 = new Car("鄂A 66666");
        var carNotIn = new Car("鄂B 66667");
        parkingLot.carIn(car1);
        parkingLot.carIn(car2);


        var allowOut = parkingLot.carOut(carNotIn);

        assertEquals(false, allowOut);
    }
    @Test
    void should_forbid_out_when_no_new_car_in_record__exist_given_car_wanna_out() {
        var parkingLot = new ParkingLot(10);
        var car1 = new Car("鄂A 88888");
        var car2 = new Car("鄂A 66666");
        parkingLot.carIn(car1);
        parkingLot.carIn(car2);
        parkingLot.carOut(car2);

        var allowOut = parkingLot.carOut(car2);

        assertEquals(false, allowOut);
    }

    @Test
    void should_set_capacity_success_and_update_remaining_capacity_when_set_capacity_given_capacity_bigger_or_equals_than_parked_car_number() {
        var parkingLot = new ParkingLot(10);
        var car1 = new Car("鄂A 88888");
        var car2 = new Car("鄂A 66666");
        parkingLot.carIn(car1);
        parkingLot.carIn(car2);

        parkingLot.setCapacity(8);

        assertEquals(8, parkingLot.getCapacity());
        assertEquals(6, parkingLot.getRemainingCapacity());
    }

    @Test
    void should_set_capacity_fail_when_set_capacity_given_capacity_less_than_parked_car_number() {
        var parkingLot = new ParkingLot(10);
        var car1 = new Car("鄂A 88888");
        var car2 = new Car("鄂A 66666");
        parkingLot.carIn(car1);
        parkingLot.carIn(car2);

        Executable executable = () -> parkingLot.setCapacity(1);

        InvalidParameterException invalidParameterException = assertThrows(InvalidParameterException.class, executable);
        assertEquals("capacity must not less than parked cars number, current parked cars number: 2.", invalidParameterException.getMessage());
    }
}
