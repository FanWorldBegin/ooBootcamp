package org.oobootcamp.parkingboy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.oobootcamp.exception.CarNotFoundException;
import org.oobootcamp.exception.NoAvailableParkingLotException;
import org.oobootcamp.exception.NoMatchedParkingLotException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;
import org.oobootcamp.parkingboy.strategy.FirstMaxSpaceStrategy;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

//  AC1
  @Test
  void should_return_a_ticket_when_boy_parking_a_car_given_a_parking_lot_with_space_and_a_car() throws Exception {
    ParkingLot parkingLotOne = new ParkingLot(2);
    ParkingBoy graduateParkingBoy =  new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLotOne);
    Car car = new Car("鄂A 88888");

    Optional<Ticket> ticket = graduateParkingBoy.parkCar(car);

    assertFalse(ticket.isEmpty());
  }

  @Test
  void should_park_in_the_third_parking_lot_and_return_a_ticket_when_boy_parking_a_car_given_three_parking_lot_first_one_space_second_two_space_third_three_space_and_a_car() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(2);
    ParkingLot parkingLot3 = new ParkingLot(3);
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2, parkingLot3);
    Car car = new Car("鄂A 88888");


    Optional<Ticket> ticket = smartParkingBoy.parkCar(car);

    assertFalse(ticket.isEmpty());
    assertEquals(car, parkingLot3.carOut(ticket.get()));
  }


  @Test
  void should_park_in_the_first_parking_lot_and_return_a_ticket_when_boy_parking_a_car_given_two_parking_lot_with_same_space_and_a_car() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2);
    Car car = new Car("鄂A 88888");


    Optional<Ticket> ticket = smartParkingBoy.parkCar(car);

    assertFalse(ticket.isEmpty());
    assertEquals(car, parkingLot1.carOut(ticket.get()));
  }

  // AC2
  @Test
  void should_parking_failed_when_boy_parking_a_car_given_a_parking_lot_with_no_space_and_a_car() throws Exception {
    ParkingLot parkingLotOne = new ParkingLot(1);
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLotOne);
    Car car1 = new Car("鄂A 88888");
    Car car2 = new Car("鄂A 88887");
    smartParkingBoy.parkCar(car1);

    Executable executable = () -> smartParkingBoy.parkCar(car2);

    assertThrows(NoAvailableParkingLotException.class, executable);
  }

  @Test
  void should_parking_failed_when_boy_parking_a_car_given_two_parking_lot_with_no_space_and_a_car() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    Car car1 = new Car("鄂A 88888");
    Car car2 = new Car("鄂A 88887");
    parkingLot1.carIn(car1);
    parkingLot2.carIn(car2);
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2);

    Executable executable = () -> smartParkingBoy.parkCar(car2);

    assertThrows(NoAvailableParkingLotException.class, executable);
  }

  // AC3
  @Test
  void should_pick_up_the_car_when_boy_pick_up_the_car_given_two_parking_lot_with_the_car_and_a_ticket() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(2);
    Car car1 = new Car("鄂A 88888");
    Car car2 = new Car("鄂A 88887");
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2);
    Optional<Ticket> ticket1 = smartParkingBoy.parkCar(car1);
    smartParkingBoy.parkCar(car2);
    Car findCar = smartParkingBoy.pickCar(ticket1.get());

    assertEquals(findCar, car1);
  }


  //AC4
  @Test
  void should_failed_when_boy_pick_up_the_car_given_two_parking_lot_without_the_car_and_a_ticket() throws Exception {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    Ticket ticket = new Ticket("鄂A 88888", LocalDateTime.now(), parkingLot1.getParkingLotNumber());
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2);

    Executable executable = ()-> smartParkingBoy.pickCar(ticket);

    assertThrows(CarNotFoundException.class, executable);
  }

  @Test
  void should_failed_when_boy_pick_up_the_car_given_two_parking_lot_without_the_car_and_a_ticket_with_wrong_parking_lot_number() {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    Ticket ticket = new Ticket("鄂A 88888", LocalDateTime.now(), UUID.randomUUID().toString());
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2);

    Executable executable = () ->  smartParkingBoy.pickCar(ticket);

    assertThrows(NoMatchedParkingLotException.class, executable);
  }


  @Test
  void should_failed_when_boy_pick_up_the_car_twice_given_two_parking_lot_with_the_car_and__a_ticket() throws Exception {
    ParkingLot parkingLot1 = new ParkingLot(1);
    ParkingLot parkingLot2 = new ParkingLot(1);
    Car car1 = new Car("鄂A 88888");
    Car car2 = new Car("鄂A 88887");
    ParkingBoy smartParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(), parkingLot1, parkingLot2);
    Optional<Ticket> ticket1 = smartParkingBoy.parkCar(car1);
    smartParkingBoy.parkCar(car2);
    smartParkingBoy.pickCar(ticket1.get());

    Executable executable = ()-> smartParkingBoy.pickCar(ticket1.get());

    assertThrows(CarNotFoundException.class, executable);
  }

}