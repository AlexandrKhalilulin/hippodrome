import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseJUnitTest {
    Horse allArgsHorse = new Horse("loshadka", 1, 3);
    Horse twoArgsHorse = new Horse("loshadka", 1);

    @Test
    void getName() {
        assertEquals("loshadka", allArgsHorse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(1, allArgsHorse.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(3, allArgsHorse.getDistance());
        assertEquals(0, twoArgsHorse.getDistance());
    }

    @ParameterizedTest
    @CsvSource({
            "\s, 1.0, 1.0",
            ", 1, 1"
    })
    void initConstructorFirstParamNullOrSpace(String name, double speed, double distance) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @Test
    void initConstructorFirstParamBlank() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("", 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void initConstructorSecondParamNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("loshadka", -2, 1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void initConstructorThirdParamNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("loshadka", 1, -2));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void moveUseGetRandomDoubleWithParams() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            allArgsHorse.move();
            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "2.0"
    })
    void moveSetRightDistance(double speed){
        Horse horse = new Horse("loshadka", 31, 5);
        MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class);
        horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(speed);
        double expected = horse.getDistance() + 31 * speed;

        horse.move();

        assertEquals(expected, horse.getDistance());
    }
}