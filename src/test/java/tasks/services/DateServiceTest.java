package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.helpers.data.ArrayTaskList;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    private static DateService dateService;
    private static Instant instant;


    @BeforeAll
    static void setUp() {
        dateService = new DateService(new TasksService(new ArrayTaskList()));
    }

    @BeforeEach
    void init() {
        instant = Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()));
    }


    @Test
    public void getDateMergedWithTime_WithNegative_Hour_Throws() {
        //Arrange
        String time = "-1:0";
        Date date = Date.from(instant);
        //Act //Assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, date));
    }

    @Test
    public void getDateMergedWithTime_WithNegative_Minute_Throws() {
        //Arrange
        String time = "0:-1";
        Date date = Date.from(instant);
        //Act //Assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, date));
    }

    @Test
    public void getDateMergedWithTime_WithPositive_Hour_Ok() {
        //Arrange
        String time = "1:00";
        Date date = Date.from(instant);
        Date result;

        //Act
        result = dateService.getDateMergedWithTime(time, date);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(result);

        //Assert
        assertEquals(calendar.get(Calendar.HOUR), 1);
    }

    @Test
    public void getDateMergedWithTime_WithPositive_Minutes_Ok() {
        //Arrange
        String time = "0:1";
        Instant instant = Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Date result;

        //Act
        result = dateService.getDateMergedWithTime(time, date);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(result);
        //Assert
        assertEquals(calendar.get(Calendar.MINUTE), 1);
    }

    @Test
    public void getDateMergedWithTime_WithMax_Hour_Ok() {
        //Arrange
        String time = "24:00";
        Date date = Date.from(instant);
        Date result;

        //Act
        result = dateService.getDateMergedWithTime(time, date);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(result);
        //Assert
        assertEquals(calendar.get(Calendar.HOUR), 0);
    }

    @Test
    public void getDateMergedWithTime_WithMax_Minute_Ok() {
        //Arrange
        String time = "0:60";
        Date date = Date.from(instant);
        Date result;

        //Act
        result = dateService.getDateMergedWithTime(time, date);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(result);
        //Assert
        assertEquals(calendar.get(Calendar.MINUTE), 0);
    }

    @Test
    public void getDateMergedWithTime_WithGreaterThanMax_Hour_Throws() {
        //Arrange
        String time = "25:0";
        Date date = Date.from(instant);
        //Act //Assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, date));

    }

    @Test
    public void getDateMergedWithTime_WithGreaterThanMax_Minute_Throws() {
        //Arrange
        String time = "0:61";
        Date date = Date.from(instant);
        //Act //Assert
        assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, date));
    }
}
