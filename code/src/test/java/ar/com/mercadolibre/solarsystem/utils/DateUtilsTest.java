package ar.com.mercadolibre.solarsystem.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class DateUtilsTest {

    private Date startDate;

    @Before
    public void init() {
        startDate = new Date();
    }

    @Test
    public void whenYearIsZeroThenReturnZero() {
        assertEquals(0, DateUtils.getDaysFrom(startDate, 0));
    }

    @Test
    public void whenYearIsNotZeroThenReturn365() {
        assertTrue(DateUtils.getDaysFrom(startDate, 1) >= 365);
    }
}
