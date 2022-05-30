package org.oobootcamp.parkingboy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.oobootcamp.exception.CarNotFoundException;
import org.oobootcamp.exception.NotFoundParkingBoyException;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;
import org.oobootcamp.parkingboy.strategy.FirstFreeStrategy;
import org.oobootcamp.parkingboy.strategy.FirstMaxSpaceStrategy;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingManagerTest {

  @Nested
  class AC1 {
    @Test
    void should_manager_park_the_car_when_park_a_car_given_a_manager_without_parking_boys_and_a_parking_lot_with_space() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingManager parkingManager = new ParkingManager(new HashSet<>(), parkingLot1);

      ParkingBoy parkingBoy = parkingManager.findParkingBoy();

      Assertions.assertEquals(parkingManager, parkingBoy);
    }

    @Test
    void should_boy_park_the_car_when_park_a_car_given_a_manager_and__a_parking_lot_without_space_and_a_parking_boy_with_a_parking_lot_with_space() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingLot parkingLot2 = new ParkingLot(1);
      ParkingBoy graduateParkingBoy = new ParkingBoy(new FirstFreeStrategy(),parkingLot2);
      parkingLot1.carIn(new Car("A888888"));
      HashSet<ParkingBoy> managedParkingBoys = new HashSet<>();
      managedParkingBoys.add(graduateParkingBoy);
      ParkingManager parkingManager = new ParkingManager(managedParkingBoys, parkingLot1);

      ParkingBoy parkingBoy = parkingManager.findParkingBoy();

      Assertions.assertEquals(graduateParkingBoy, parkingBoy);
    }

    @Test
    void should_manager_park_the_car_when_park_a_car_given_a_manager_and_a_parking_lot_with_space_and_a_parking_boy_with_a_parking_lot_without_space() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingLot parkingLot2 = new ParkingLot(1);
      ParkingBoy graduateParkingBoy = new ParkingBoy(new FirstFreeStrategy(),parkingLot2);
      parkingLot2.carIn(new Car("A888888"));
      HashSet<ParkingBoy> managedParkingBoys = new HashSet<>();
      managedParkingBoys.add(graduateParkingBoy);
      ParkingManager parkingManager = new ParkingManager(managedParkingBoys, parkingLot1);

      ParkingBoy parkingBoy = parkingManager.findParkingBoy();

      Assertions.assertEquals(parkingManager, parkingBoy);
    }
  }

  @Nested
  class AC2 {
    @Test
    void should_throw_exception_when_park_a_car_given_a_manager_with_a_parking_lot_without_space_and_with_zero_parking_boy() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      parkingLot1.carIn(new Car("A888888"));
      ParkingManager parkingManager = new ParkingManager(new HashSet<>(), parkingLot1);

      Executable executable = ()-> parkingManager.findParkingBoy();;

      assertThrows(NotFoundParkingBoyException.class, executable);
    }

    @Test
    void should_throw_exception_when_park_a_car_given_a_manager_with_a_parking_lot_without_space_and_a_parking_boy_with_a_parking_lot_without_space() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingLot parkingLot2 = new ParkingLot(1);
      ParkingBoy graduateParkingBoy = new ParkingBoy(new FirstFreeStrategy(),parkingLot2, parkingLot1);
      parkingLot1.carIn(new Car("A888888"));
      parkingLot2.carIn(new Car("A888887"));
      HashSet<ParkingBoy> managedParkingBoys = new HashSet<>();
      managedParkingBoys.add(graduateParkingBoy);
      ParkingManager parkingManager = new ParkingManager(managedParkingBoys, parkingLot1);

      Executable executable = ()-> parkingManager.findParkingBoy();;

      assertThrows(NotFoundParkingBoyException.class, executable);
    }

  }


  @Nested
  class AC3 {
    @Test
    void should_find_manager_when_pick_a_car_given_a_manager_with_a_parking_lot_with_the_car_and_a_ticket() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      Optional<Ticket> ticket = parkingLot1.carIn(new Car("A888888"));
      ParkingManager parkingManager = new ParkingManager(new HashSet<>(), parkingLot1);

      ParkingBoy parkingBoy = parkingManager.findParkingBoyByTicket(ticket.get());

      Assertions.assertEquals(parkingManager, parkingBoy);
    }

    @Test
    void should_find_boy_when_pick_a_car_given_a_manager_with_a_parking_lot_without_the_car_and_a_boy_with_a_parking_lot_with_the_car_a_ticket() throws InterruptedException {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingLot parkingLot2 = new ParkingLot(1);
      Optional<Ticket> ticket = parkingLot2.carIn(new Car("A888888"));
      ParkingBoy graduateParkingBoy = new ParkingBoy(new FirstFreeStrategy(),parkingLot2);
      HashSet<ParkingBoy> managedParkingBoys = new HashSet<>();
      managedParkingBoys.add(graduateParkingBoy);
      ParkingManager parkingManager = new ParkingManager(managedParkingBoys, parkingLot1);
      ParkingBoy parkingBoy = parkingManager.findParkingBoyByTicket(ticket.get());

      Assertions.assertEquals(graduateParkingBoy, parkingBoy);
    }

  }


  @Nested
  class AC4 {
    @Test
    void should_throw_exception_when_park_a_car_given_a_ticket_and_a_manager_with_a_parking_lot_without_the_car_and_with_zero_parking_boy() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingManager parkingManager = new ParkingManager(new HashSet<>(), parkingLot1);
      Ticket ticket = new Ticket("鄂A 88888", LocalDateTime.now(), UUID.randomUUID().toString());

      Executable executable = ()-> parkingManager.findParkingBoyByTicket(ticket);

      assertThrows(NotFoundParkingBoyException.class, executable);
    }

    @Test
    void should_throw_exception_when_park_a_car_given_a_manager_with_a_parking_lot_without_space_and_a_parking_boy_with_a_parking_lot_without_space() {
      ParkingLot parkingLot1 = new ParkingLot(1);
      ParkingLot parkingLot2 = new ParkingLot(1);
      ParkingBoy graduateParkingBoy = new ParkingBoy(new FirstMaxSpaceStrategy(),parkingLot2);
      HashSet<ParkingBoy> managedParkingBoys = new HashSet<>();
      managedParkingBoys.add(graduateParkingBoy);
      ParkingManager parkingManager = new ParkingManager(managedParkingBoys, parkingLot1);
      Ticket ticket = new Ticket("鄂A 88888", LocalDateTime.now(), UUID.randomUUID().toString());

      Executable executable = ()-> parkingManager.findParkingBoyByTicket(ticket);

      assertThrows(NotFoundParkingBoyException.class, executable);
    }

  }

}