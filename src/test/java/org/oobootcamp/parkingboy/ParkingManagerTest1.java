package org.oobootcamp.parkingboy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.oobootcamp.parking.Car;
import org.oobootcamp.parking.ParkingLot;
import org.oobootcamp.parking.Ticket;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest1 {

  @Nested
  class AC1 {
    @Test
    void should_return_a_ticket_when_park_a_car_given_a_parking_lot_with_space_and_a_car_and_manager_with_no_parking_boy() {
    }
  }

  @Nested
  class AC2 {
    @Test
    void should_fail_when_park_a_car_given_a_parking_lot_without_space_and_a_car_and_manager_with_no_parking_boy() {
    }
  }


  @Nested
  class AC3 {
    @Test
    void should_return_a_ticket_when_park_a_car_given_a_graduate_boy_with_a_parking_lot_with_space_and_a_car() {
    }

    @Test
    void should_return_a_ticket_when_park_a_car_given_a_smart_boy_with_a_parking_lot_with_space_and_a_graduate_boy_with_a_full_parking_lot_and_a_car() {
    }

    @Test
    void should_return_a_ticket_when_park_a_car_given_a_smart_boy_with_a_full_parking_lot_a_maranger_with_a_available_parking_lot_and_a_car() {
    }

  }

  @Nested
  class AC4 {
    @Test
    void should_failed_when_park_a_car_given_a_manager_parking_lot_without_space_and_a_smart_boy_with_a_full_parking_lot_and_a_car() {
    }

    @Test
    void should_fail_when_park_a_car_given_a_manager_parking_lot_without_space_and_a_smart_boy_with_a_full_parking_lot_and_a_graduate_boy_with_a_full_parking_lot_and_a_car() {
    }
  }

  @Nested
  class AC5 {
    @Test
    void  should_success_when_manager_pick_up_the_car_given_a_ticket_and_a_parking_lot_with_the_car_and_no_boy() {

    }
  }


  @Nested
  class AC6 {
    @Test
    void  should_fail_when_manager_pick_up_the_car_given_a_ticket_and_a_parking_lot_without_the_car_and_no_boy() {

    }
  }

  @Nested
  class AC7 {
    @Test
    void  should_fail_when_manager_pick_up_the_car_given_a_ticket_and_a_parking_lot_without_the_car_and_no_boy() {

    }
  }

  @Nested
  class AC8 {
    @Test
    void  should_fail_when_pick_up_the_car_given_a_ticket_and_a_manager_parking_lot_without_the_car_and_a_smart_boy_with_a_parking_lot_with_out_the_car() {

    }
  }

}