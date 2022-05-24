package org.oobootcamp.warmup.length;

public class FizzBuzz {

    public String convert(int input) {

        if(input % 3 == 0) {
            return "fizz";
        } else if(input % 5 == 0) {
            return "buzz";
        }

        return null;
    }
}
