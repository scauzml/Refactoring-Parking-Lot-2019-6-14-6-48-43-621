package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    public void should_return_right_or_false_when_get_a_car() {
         //given
        Customer customer=new Customer();
        Car car = new Car();
        customer.setCar(car);
        //when
        boolean isMycar=customer.isMyCar(car);
        //then
        Assertions.assertEquals(true,isMycar);
    }


}
