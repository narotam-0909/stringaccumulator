package com.ubs.accumulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringAccumulatorTest {
    @Test
    public void returnZeroOnEmptyString(){
        assertEquals(0,StringAccumulator.add(""));
    }

    @Test
    public void returnNumber(){
        assertEquals(1,StringAccumulator.add("1"));
    }

    @Test
    public void returnSumOfNumberDelimitedByComma(){
        assertEquals(3,StringAccumulator.add("1,2"));
    }

    @Test
    public void returnSumOfMultipleNumbers(){
        assertEquals(6,StringAccumulator.add("1,2,3"));
    }

    @Test
    public void acceptNewLineAsValidDelimiter(){
        assertEquals(6,StringAccumulator.add("1\n2,3"));
    }

    @Test
    public void acceptCustomDelimitedSyntax(){
        assertEquals(3,StringAccumulator.add("//;\n1;2"));
    }


    @Test
    public void raiseExceptionOnNegatives(){
        try{
            StringAccumulator.add("-1,2,3");
            fail("Exception Excepted");
        }catch (RuntimeException ex) {

        }
    }

    @Test
    public void exMessageShouldContainsTheNegativeValue(){
        try{
            StringAccumulator.add("-1,-2,3");
            fail("Exception Excepted");
        }catch (RuntimeException ex) {
            assertEquals("negatives not allowed : [-1, -2]",ex.getMessage());
        }

    }

    @Test
    public void excludeNumberGreaterThanThousands(){
            assertEquals(6,StringAccumulator.add("1,2,3,1001,1002"));
    }

    @Test
    public void allowDelimitersOfAnyLength(){
        assertEquals(6,StringAccumulator.add("//***\n1***2***3"));
    }

    @Test
    public void allowMultipleDelimiters(){
        assertEquals(6,StringAccumulator.add("//*|%\n1*2%3"));
    }
}
