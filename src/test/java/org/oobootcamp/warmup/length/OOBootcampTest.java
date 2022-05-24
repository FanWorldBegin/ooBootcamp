package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OOBootcampTest {

    private FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    void should_welcome_you_come_oo_bootcamp() {
        OOBootcamp ooBootcamp = new OOBootcamp("Hello, Welcome to OOBootcamp");
        assertThat(ooBootcamp.message()).isEqualTo("Hello, Welcome to OOBootcamp");
    }



    @Test
    void should_return_number() {
//        given
        Integer input = 1;
//        when
        String result = fizzBuzz.convert(input);

//        then
        Assertions.assertEquals(1, result);
    }

    @Test
    void should_return_fizz() {
//        given
        Integer input = 3;
//        when
        String result = fizzBuzz.convert(input);

//        then
        Assertions.assertEquals("fizz", result);
    }

    @Test
    void should_return_buzz() {
//        given
        Integer input = 5;
//        when
        String result = fizzBuzz.convert(input);

//        then
        Assertions.assertEquals("buzz", result);
    }

    @Test
    void should_return_fizzbuzz() {
//        given
        Integer input = 15;
//        when
        String result = fizzBuzz.convert(input);

//        then
        Assertions.assertEquals("fizzbuzz", result);
    }

}
