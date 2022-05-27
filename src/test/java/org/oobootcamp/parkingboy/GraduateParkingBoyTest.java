package org.oobootcamp.parkingboy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.oobootcamp.exception.NoAvailableParkingLotException;
import org.oobootcamp.exception.NoMatchedParkingLotException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GraduateParkingBoyTest {
//  AC1
    @Test
    void should_return_a_ticket_when_boy_parking_a_car_given_a_parking_lot_with_space_and_a_car() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        Car car = new Car("鄂A 88888");

        Optional<Ticket> ticket = graduateParkingBoy.parkCar(car);

        assertEquals(car.platNumber(), ticket.get().platNumber());
    }

    @Test
    void should_parking_failed_when_boy_parking_a_car_given_a_parking_lot_with_no_space_and_a_car() throws Exception {
        ParkingLot parkingLotOne = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotOne));
        Car car1 = new Car("鄂A 88888");
        Car car2 = new Car("鄂A 88887");
        graduateParkingBoy.parkCar(car1);

        Executable executable = () -> graduateParkingBoy.parkCar(car2);

        assertThrows(NoAvailableParkingLotException.class, executable);
    }

//  AC2
    @Test
    void should_park_car_in_the_first_parking_lot_and_return_a_ticket_when_boy_parking_a_car_given_two_parking_lot_with_space_and_a_car() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(1);

        Car car1 = new Car("鄂A 88888");
        Car car2 = new Car("鄂A 88887");

        parkingLot2.carIn(car2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        Optional<Ticket> ticket = graduateParkingBoy.parkCar(car1);

        assertNotNull(ticket);
        assertEquals(parkingLot1.getParkingLotNumber(), ticket.get().parkingLotNumber());
    }

    @Test
    void should_park_car_in_the_second_parking_lot_and_return_a_ticket_when_boy_parking_a_car_given_two_parking_lot_only_the_second_one_with_space_and_a_car() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);

        Car car1 = new Car("鄂A 88888");
        Car car2 = new Car("鄂A 88887");

        parkingLot1.carIn(car1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        Optional<Ticket> ticket = graduateParkingBoy.parkCar(car2);

        assertNotNull(ticket);
        assertEquals(parkingLot2.getParkingLotNumber(), ticket.get().parkingLotNumber());
    }

    @Test
    void should_parking_failed_when_boy_parking_a_car_given_two_parking_lot_with_no_space_and_a_car() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);

        Car car1 = new Car("鄂A 88888");
        Car car2 = new Car("鄂A 88887");

        parkingLot1.carIn(car1);
        parkingLot2.carIn(car2);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        Executable executable = () -> graduateParkingBoy.parkCar(car2);

        assertThrows(NoAvailableParkingLotException.class, executable);
    }

//    AC3

    @Test
    void should_pick_up_the_car_when_boy_pick_up_the_car_given_two_parking_lot_with_the_car_and_a_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);

        Car car1 = new Car("鄂A 88888");
        Car car2 = new Car("鄂A 88887");
        
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        Optional<Ticket> ticket1 = graduateParkingBoy.parkCar(car1);
        graduateParkingBoy.parkCar(car2);

        boolean findCar = graduateParkingBoy.pickCar(ticket1.get());

       assertTrue(findCar);


    }

    @Test
    void should_failed_when_boy_pick_up_the_car_given_two_parking_lot_without_the_car_and_a_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);

        Ticket ticket = new Ticket("鄂A 88888", LocalDateTime.now(), parkingLot1.getParkingLotNumber());

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        boolean findCar = graduateParkingBoy.pickCar(ticket);

        assertFalse(findCar);
    }

    @Test
    void should_failed_when_boy_pick_up_the_car_given_two_parking_lot_without_the_car_and_a_ticket_with_wrong_parking_lot_number() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);

        Ticket ticket = new Ticket("鄂A 88888", LocalDateTime.now(), 1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        Executable executable = () ->  graduateParkingBoy.pickCar(ticket);
        assertThrows(NoMatchedParkingLotException.class, executable);
    }


    @Test
    void should_failed_when_boy_pick_up_the_car_twice_given_two_parking_lot_with_the_car_and__a_ticket() throws Exception {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);

        Car car1 = new Car("鄂A 88888");
        Car car2 = new Car("鄂A 88887");

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLot2, parkingLot1));

        Optional<Ticket> ticket1 = graduateParkingBoy.parkCar(car1);
        graduateParkingBoy.parkCar(car2);
        graduateParkingBoy.pickCar(ticket1.get());


        boolean findCar = graduateParkingBoy.pickCar(ticket1.get());

        assertFalse(findCar);
    }

}