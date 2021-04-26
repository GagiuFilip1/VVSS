package tasks.dataContext;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskDataContextTest {
    @Test
    void getFormattedInterval_WithNoInterval_ThrowException() {
        //Arrange
        int interval = 0;
        //Act //Assert
        assertThrows(IllegalArgumentException.class, () -> TaskDataContext.getFormattedInterval(interval));
    }

    @Test
    void getFormattedInterval_WithHoursAndMinutes_Ok() {
        //Arrange
        int interval = 7740;
        //Act //Assert
        var parsedInterval = TaskDataContext.getFormattedInterval(interval);
        assertEquals("2 hours 9 minutes ", parsedInterval);
    }

    @Test
    void getFormattedInterval_WithDaysAndSeconds_Ok() {
        //Arrange
        int interval = 172804;
        //Act //Assert
        var parsedInterval = TaskDataContext.getFormattedInterval(interval);
        assertEquals("2 days 0 hour 0 minute 4 seconds ", parsedInterval);
    }
}